package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import dao.CourseDao;
import dao.DbConnection;
import dao.StudentCourseDao;
import dao.StudentDao;
import model.Course;
import model.Student;

/////////////////// CONTROLADOR ESTUDIANTES - ALUMNOS ////////////////////////
@WebServlet("/student")
@MultipartConfig
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * RECIBIR PETICIONES GET
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		RequestDispatcher rd;
		DbConnection conn = new DbConnection();
		
		//En funcion de la accion actuaremos
		switch(action) {
			//MOSTRAR FORMULARIO DE NUEVO ALUMNO
			case "add":
				if(session.getAttribute("admin") != null) {
					/// Enviar informacion sobre logueo ///
					if(session.getAttribute("admin") != null)
						req.setAttribute("user", "admin");
					else if(session.getAttribute("student") != null)
						req.setAttribute("user", "student");
					//////////////////////////////////
					
					rd = req.getRequestDispatcher("/addstudent.jsp");
			        rd.forward(req, resp);
				}else {
					resp.sendRedirect(req.getContextPath() + "/login");
				}
				break;
				
			//MOSTRAR ALUMNO
			case "show":
				int id = Integer.parseInt(req.getParameter("id"));
				//Obtener datos del alumno
				StudentDao studentDao = new StudentDao(conn);
		        Student student = studentDao.getById(id);
		        
		        //Comprobar si se ha encontrado el usuario indicado
				if(student.getId()>-1) {
					//Cambiar el formato de fecha para enviarlo a la vista
					SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
					String newDate = formatter.format(student.getBirthDate());
					req.setAttribute("newDate", newDate);
					
					//Calcular edad para enviarlo a la vista 
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate birthd = LocalDate.parse(newDate, fmt);
					LocalDate now = LocalDate.now();
					Period age = Period.between(birthd, now);
					req.setAttribute("age", age.getYears());
					
					//Comprobar si existe una foto para el usuario e indicarlo a la vista
					String path = req.getServletContext().getRealPath("")+"uploads"; 
					File photo = new File(path+"/"+id+".jpg");
					if(photo.exists())
						req.setAttribute("existPhoto", true);
					else
						req.setAttribute("existPhoto", false);
					
					//Comprobar si existe una foto para el usuario e indicarlo a la vista
					File curri = new File(path+"/"+id+".pdf");
					if(curri.exists())
						req.setAttribute("existCurri", true);
					else
						req.setAttribute("existCurri", false);
					
					//Obtener todos los cursos
			        CourseDao courseDao = new CourseDao(conn);
			        List<Course> allCourses= courseDao.getAll(); 
					
					//Obtener los cursos asociados con el alumno
			        StudentCourseDao scDao = new StudentCourseDao(conn);
			        List<Integer> sc = scDao.getCoursesStudent(id); 
					
			        //Obtener objetos de tipo curso asociados al alumno
			        List<Course> courses = new LinkedList<>();
			        for(int i=0; i<sc.size(); i++) {
			        	for(int j=0; j<allCourses.size(); j++) {
			        		if(sc.get(i)==allCourses.get(j).getId())
			        			courses.add(allCourses.get(j));
			        	}
			        }
			        
			        /// Enviar informacion sobre logueo ///
					if(session.getAttribute("admin") != null)
						req.setAttribute("user", "admin");
					else if(session.getAttribute("student") != null)
						req.setAttribute("user", "student");
					//////////////////////////////////
			        
					req.setAttribute("student", student);
					req.setAttribute("courses", courses);
					rd = req.getRequestDispatcher("/student.jsp");
			        rd.forward(req, resp);
				}
				break;
				
			// MOSTRAR FORMULARIO EDICION DE UN ALUMNO
			case "edit":
				
				int idEdit = Integer.parseInt(req.getParameter("id"));
				//Proteger el controlador de usuarios no logueados
				Student st=null;
				if(session.getAttribute("student") != null) {
					st = (Student) session.getAttribute("student");
				}
				//Solo administradores o usuarios registrados que intenten editar su propio perfil
				if(session.getAttribute("admin") != null || (st!=null && st.getId()==idEdit)) {
					//Obtener datos del alumno
					StudentDao studentDaoEdit = new StudentDao(conn);
			        Student studentEdit = studentDaoEdit.getById(idEdit);
			        
			        //Obtener todos los cursos
			        CourseDao courseDao = new CourseDao(conn);
			        List<Course> courses= courseDao.getAll(); 
			        
			        //Obtener los cursos asociados con el alumno
			        StudentCourseDao scDao = new StudentCourseDao(conn);
			        List<Integer> studentCourses= scDao.getCoursesStudent(idEdit); 
			        
			        //Comprobar si existe una foto para el usuario e indicarlo a la vista
					String path = req.getServletContext().getRealPath("")+"uploads"; 
					File photo = new File(path+"/"+idEdit+".jpg");
					if(photo.exists())
						req.setAttribute("existPhoto", true);
					else
						req.setAttribute("existPhoto", false);
			        
			        //Comprobar si se ha encontrado el usuario indicado
					if(studentEdit.getId()>-1) {
						req.setAttribute("student", studentEdit);
						req.setAttribute("courses", courses);
						req.setAttribute("studentCourses", studentCourses);
						
						/// Enviar informacion sobre logueo ///
						if(session.getAttribute("admin") != null)
							req.setAttribute("user", "admin");
						else if(session.getAttribute("student") != null)
							req.setAttribute("user", "student");
						//////////////////////////////////
						
						rd = req.getRequestDispatcher("/editstudent.jsp");
			        	rd.forward(req, resp);
					}
				}else {
					resp.sendRedirect(req.getContextPath() + "/login");
				}
				break;
				
			// ELIMINAR UN ALUMNO DE LA BASE DE DATOS
			case "remove":
				if(session.getAttribute("admin") != null) {
					int idRemove = Integer.parseInt(req.getParameter("id"));
					//Obtener datos del alumno
					StudentDao studentDaoRemove = new StudentDao(conn);
			        studentDaoRemove.remove(idRemove);
			        
			        resp.sendRedirect(req.getContextPath() + "/?action=options");
				}else {
					resp.sendRedirect(req.getContextPath() + "/login");
				}
				break;
		}
	}
	
	//-----------------------------------------------------------------------------------------------

	/**
	 * RECIBIR PETICIONES POST
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		
		//En funcion de la accion actuaremos
		switch(action) {
			
			// ACTUALIZACIÓN DE LOS DATOS DE UN ALUMNO EN LA BASE DE DATOS
			case "update":
				int sId = Integer.parseInt(req.getParameter("id"));
				
				//Proteger el controlador de usuarios no logueados
				Student st=null;
				if(session.getAttribute("student") != null) {
					st = (Student) session.getAttribute("student");
				}
				//Solo administradores o usuarios registrados que intenten editar su propio perfil
				if(session.getAttribute("admin") != null || (st!=null && st.getId()==sId)) {
				 
					 String sName = req.getParameter("name");
					 String sSurname = req.getParameter("surname");
					 String sEmail = req.getParameter("email");
					 String sPasswd = req.getParameter("passwd");
					 int sPhone = Integer.parseInt(req.getParameter("phone"));
					 
					 //Enlace linkedin
					 String sLinkedin;
					 if(req.getParameter("linkedin").length()==0)
						 sLinkedin = null;
					 else
						 sLinkedin = req.getParameter("linkedin");
					 
					 //Enlace github
					 String sGithub;
					 if(req.getParameter("github").length()==0)
						 sGithub = null;
					 else
						 sGithub = req.getParameter("github");
					 
					//Observaciones
					 String sObservations;
					 if(req.getParameter("observations").length()==0)
						 sObservations = null;
					 else
						 sObservations = req.getParameter("observations");		 
					
					 //Recuperar fecha indicada
					 Date sBirth = new Date();
					 try {
						 String date = req.getParameter("birth");
						 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						 sBirth = format.parse(date);
					 } catch (ParseException e) {
						e.printStackTrace();
					 }
					 
					 //Obtener si esta disponible o no
					 boolean sAvailable = true;
					 if(req.getParameter("status").equals("false")) {
						 sAvailable = false;
					 }				 			  
					 
					 //Llamada al metodo de actualizar
					 DbConnection conn = new DbConnection();
					 StudentDao studentDao = new StudentDao(conn);
					 studentDao.update(sId, sName, sSurname, sEmail, sPhone, sLinkedin, sGithub, sObservations, sBirth, sAvailable);
					 
					 //Obtener cursos seleccionados y actualizar tabla intermedia
					 StudentCourseDao scDao = new StudentCourseDao(conn);			 
					 scDao.removeAllStudent(sId); //Eliminar las asociaciones para realizar las nuevas
					 
					 if( req.getParameterValues("selected")!=null) {
						 String checks[] = req.getParameterValues("selected");
					 	 //Insertar asociaciones marcadas
						 for(int i=0;i<checks.length;i++) {
							 scDao.add(sId, Integer.parseInt(checks[i]));
						 }
					 }
					 
					 //Rutas
					 String path = req.getServletContext().getRealPath("")+"uploads"; 
					 File dir = new File(path);
					 dir.mkdirs(); //Crear el directorio por si no esta creado 
					 
					 //Almacenar el curriculum si se ha examinado
					 if(req.getPart("curriculum").getSize()!=0){
						Part curriculum = req.getPart("curriculum"); // Obtener el archivo		 
				 	    String fileName = Paths.get(curriculum.getSubmittedFileName()).getFileName().toString();
				 	    String[] p = fileName.split("\\.");
				 	    String extension = p[p.length-1];
				 	    
				 	    //Asegurarse de que el fichero tiene la extension adecuada
				 	    if(extension.equalsIgnoreCase("pdf")){
				 	    	InputStream content = curriculum.getInputStream();
					 	    File file = new File(path+"/"+sId+".pdf");
					 	    //Guardar fichero en el directorio adecuado	 	    
					 	    saveUploadFile(file, content);
				 	    }
					 }
					 
					 //Almacenar la imagen si se ha examinado
					 if(req.getPart("photo").getSize()!=0){
						Part curriculum = req.getPart("photo"); // Obtener el archivo		 
				 	    String fileName = Paths.get(curriculum.getSubmittedFileName()).getFileName().toString();
				 	    String[] p = fileName.split("\\.");
				 	    String extension = p[p.length-1];
				 	    
				 	    //Asegurarse de que el fichero tiene la extension adecuada
				 	    if(extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png")) {
				 	    	InputStream content = curriculum.getInputStream();
					 	    File file = new File(path+"/"+sId+".jpg");
					 	    //Guardar fichero en el directorio adecuado	 	    
					 	    saveUploadFile(file, content);
				 	    }
					 }
					 
					 //Actualizar la contraseña si se ha cambiado
					 if(!sPasswd.equals("00000000") && !sPasswd.equals("")) {
						 studentDao.updatePass(sPasswd, sId);
					 }
					 
					 conn.disconnect();
					 resp.sendRedirect(req.getContextPath() + "/student?action=show&id="+sId);
					 
				}else {
					resp.sendRedirect(req.getContextPath() + "/login");
				}
				break;
				 
			// PROCESO DE ALMACENAR UN NUEVO ALUMNO EN LA BASE DE DATOS
			case "store":
				if(session.getAttribute("admin") != null) {
					 String sNameAdd = req.getParameter("name");
					 String sSurnameAdd = req.getParameter("surname");
					 String sUsernameAdd = req.getParameter("username");
					 String sPasswdAdd = req.getParameter("passwd");
					 
					 //Llamada al metodo de insertar
					 DbConnection connInsert = new DbConnection();
					 StudentDao studentDaoInsert = new StudentDao(connInsert);
					 int newId = studentDaoInsert.add(sNameAdd, sSurnameAdd, sUsernameAdd, sPasswdAdd);
					 
					 resp.sendRedirect(req.getContextPath() + "/student?action=show&id="+newId);
				}else {
					resp.sendRedirect(req.getContextPath() + "/login");
				}
				break;
		}
	}

	//-----------------------------------------------------------------------------------------------
	
	/**
	 * METODO PARA ALMACENAR UN FICHERO A PARTIR DE UN FLUJO DE ENTRADA
	 * @param fileOutput
	 * @param in
	 */
	public void saveUploadFile(File fileOutput, InputStream in) {
		System.out.println(fileOutput);
		 OutputStream outputStream = null;
 	     try{
 	        outputStream = new FileOutputStream(fileOutput);
 	        int read = 0;
 	        byte[] bytes = new byte[1024];
 	        //Escribir el archivo de salida
 	        while ((read = in.read(bytes)) != -1) {
 	            outputStream.write(bytes, 0, read);
 	        }
 	        
 	     }catch (Exception e) {
 	    	e.printStackTrace();
 	     }finally{
 	        if(outputStream != null){
 	            try {
 	            	//Cerrar flujo
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
 	        }
 	     }
 	}
}