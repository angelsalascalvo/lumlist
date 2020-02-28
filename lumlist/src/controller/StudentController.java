package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.DbConnection;
import dao.StudentDao;
import model.Student;

/**
 * Servlet implementation class StudientController
 */
@WebServlet("/student")
@MultipartConfig
public class StudentController extends HttpServlet {
	
	private static final String UPLOAD_DIR = "uploads";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		RequestDispatcher rd;
		DbConnection conn = new DbConnection();
		
		//En funcion de la accion actuaremos
		switch(action) {
			case "add":
				rd = req.getRequestDispatcher("/addstudent.jsp");
		        rd.forward(req, resp);
				break;
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
					
					req.setAttribute("student", student);
					rd = req.getRequestDispatcher("/student.jsp");
			        rd.forward(req, resp);
				}
				
				break;		
			case "edit":
				int idEdit = Integer.parseInt(req.getParameter("id"));
				//Obtener datos del alumno
				StudentDao studentDaoEdit = new StudentDao(conn);
		        Student studentEdit = studentDaoEdit.getById(idEdit);
		        
		        //Comprobar si se ha encontrado el usuario indicado
				if(studentEdit.getId()>-1) {
					req.setAttribute("student", studentEdit);
					rd = req.getRequestDispatcher("/editstudent.jsp");
		        	rd.forward(req, resp);
				}
				break;
		}
	}
	
	/**
	 * RECEPCION DEL FORMULARIO DE ACTUALIZACION DE UN ESTUDIANTE
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 int sId = Integer.parseInt(req.getParameter("id"));
		 String sName = req.getParameter("name");
		 String sSurname = req.getParameter("surname");
		 String sEmail = req.getParameter("email");
		 int sPhone = Integer.parseInt(req.getParameter("phone"));
		 
		 //Enlace linkedin
		 String sLinkedin;
		 if(req.getParameter("linkedin").length()==0)
			 sLinkedin = null;
		 else
			 sLinkedin = req.getParameter("linkedin");
		 
		 //Enlace github
		 String sGithub;
		 if(req.getParameter("linkedin").length()==0)
			 sGithub = null;
		 else
			 sGithub = req.getParameter("github");
		 
		//Observaciones
		 String sObservations;
		 if(req.getParameter("linkedin").length()==0)
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		 //Llamada al metodo de actualizar
		 DbConnection conn = new DbConnection();
		 StudentDao studentDao = new StudentDao(conn);
		 studentDao.update(sId, sName, sSurname, sEmail, sPhone, sLinkedin, sGithub, sObservations, sBirth);
		 
		 
		 String applicationPath = req.getServletContext().getRealPath(""); 
		 String uploadFilePath = applicationPath + UPLOAD_DIR;
		 File dir = new File(uploadFilePath);
		 dir.mkdirs();
		 
		 
		 
 		 Part curriculum = req.getPart("curriculum"); // Obtiene el archivo		 
 	    String fileName = Paths.get(curriculum.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
 	    InputStream content = curriculum.getInputStream();
 	    File file = new File(uploadFilePath+"/"+fileName);
 	    
 	    
 	    saveUploadFile(file, content);
 		 
		   
		   
	



	}

	/**
	 * METODO PARA ALMACENAR UN FICHERO A PARTIR DE UN FLUJO DE ENTRADA
	 * @param fileOutput
	 * @param in
	 */
	public void saveUploadFile(File fileOutput, InputStream in) {
		System.out.println(fileOutput.getAbsolutePath());
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
