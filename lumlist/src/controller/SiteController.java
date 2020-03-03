package controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.CourseDao;
import dao.DbConnection;
import dao.StudentCourseDao;
import dao.StudentDao;
import model.Course;
import model.Student;

/////////////////// CONTROLADOR DE PAGINAS GENERALES DEL SITIO ////////////////////////
@WebServlet("/index")
public class SiteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * RECIBIR PETICIONES GET
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		RequestDispatcher rd;
		DbConnection conn = new DbConnection();
		
		if(req.getParameter("action")==null) {
			/// Enviar informacion sobre logueo ///
			if(session.getAttribute("admin") != null)
				req.setAttribute("user", "admin");
			else if(session.getAttribute("student") != null)
				req.setAttribute("user", "student");
			//////////////////////////////////
			
			rd = req.getRequestDispatcher("/index.jsp");
	        rd.forward(req, resp);
		}else {		
			//En funcion de la accion actuaremos
			switch(action) {
			
				// BUSQUEDA DE ALUMNOS
				case "search":
					
					String key = req.getParameter("key");
					StudentDao studenDao = new StudentDao(conn);
					List<Student> students = new LinkedList<>(); 

					if(key==null || key.equalsIgnoreCase("all") ) {
						students = studenDao.getAll();
						for(int i=0; i<students.size(); i++) {
							System.out.println(students.get(i).getName());
						}
					}else {
						students = studenDao.find(key);
						for(int i=0; i<students.size(); i++) {
							System.out.println(students.get(i).getName());
						}
					}
					
					//Obtener los nombre de los cursos asociados a cada alumno
					for(int i=0; i<students.size(); i++) {
						//Obtener listado de cursos asociados 
				        StudentCourseDao scDao = new StudentCourseDao(conn);
				        List<Integer> sc = scDao.getCoursesStudent(students.get(i).getId());
				        List<String> cNames = new LinkedList<>(); 
				        //Recuperar nombres de los cursos
				        CourseDao courseDao = new CourseDao(conn);
				        for(int j = 0; j<sc.size(); j++) {
				        	String name= courseDao.getName(sc.get(j));
				        	cNames.add(name);
				        }
				        students.get(i).setCoursesNames(cNames);
				        
				        //Comprobar si tiene foto
				        String path = req.getServletContext().getRealPath("")+"uploads"; 
						File photo = new File(path+"/"+students.get(i).getId()+".jpg");
						if(photo.exists())
							students.get(i).setHasPhoto(true);
						else
							students.get(i).setHasPhoto(false);
					}
					
					//FILTROS
					
					//Estado
					if(req.getParameter("status")!=null) {
						List<Student> newStudents = new LinkedList<>(); 
						
						String status = req.getParameter("status");
						//Obtener los nombre de los cursos asociados a cada alumno
						for(int i=0; i<students.size(); i++) {
							//Solo Disponibles
							if(status.equalsIgnoreCase("true")) {
								if(students.get(i).isAvailable())
									newStudents.add(students.get(i));
								
							//Solo Ocupados
							}else if(status.equalsIgnoreCase("false")) {
								if(!students.get(i).isAvailable())
									newStudents.add(students.get(i));
							}
						}
						
						students = new LinkedList<>();
						students = newStudents;
					}

					
					//Curso
					if(req.getParameter("course")!=null) {
						List<Student> newStudents = new LinkedList<>(); 
						
						String course = req.getParameter("course");
						//Comprobar que cada estudiante pertenece al curso indicado
						for(int i=0; i<students.size(); i++) {
							boolean in = false;
							for(int j=0; j<students.get(i).getCoursesNames().size(); j++) {
								if(students.get(i).getCoursesNames().get(j).equalsIgnoreCase(course))
									in=true;
							}
							//Si pertenece lo agregamos 
							if(in)
								newStudents.add(students.get(i));
						}
						
						students = new LinkedList<>();
						students = newStudents;
					}
					
					//Obtener todos los cursos
			        CourseDao courseDao = new CourseDao(conn);
			        List<Course> courses = courseDao.getAll();
			        
			        //Enviar los datos de busqueda a la vista
					if(key==null || !key.equalsIgnoreCase("all"))
						req.setAttribute("key", key);
					else
						req.setAttribute("key", "");
					
					/// Enviar informacion sobre logueo ///
					if(session.getAttribute("admin") != null)
						req.setAttribute("user", "admin");
					else if(session.getAttribute("student") != null)
						req.setAttribute("user", "student");
					//////////////////////////////////
					
			        req.setAttribute("courses", courses);
					req.setAttribute("students", students);
					rd = req.getRequestDispatcher("/search.jsp");
			        rd.forward(req, resp);
					break;
					
				//OPCIONES DEL ADMINISTRADOR
				case "options":
					if(session.getAttribute("admin") != null) {
						/// Enviar informacion sobre logueo ///
						if(session.getAttribute("admin") != null)
							req.setAttribute("user", "admin");
						else if(session.getAttribute("student") != null)
							req.setAttribute("user", "student");
						//////////////////////////////////
						
						rd = req.getRequestDispatcher("/options.jsp");
				        rd.forward(req, resp);
					}else {
						resp.sendRedirect(req.getContextPath() + "/login");
					}
					break;
				
				// MOSTRAR PERFIL DEL USUARIO REGISTRADO
				case "account":
					//Obtener usuario a traves de la variable de sesion
					if(session.getAttribute("student") != null) {
						Student student = (Student) session.getAttribute("student");
						resp.sendRedirect(req.getContextPath() + "/student?action=edit&id="+student.getId());
						 break;
					}else {
						resp.sendRedirect(req.getContextPath() + "/login");
					}
					break;
					
				// MOSTRAR CRÉDITOS
				case "credits":
					rd = req.getRequestDispatcher("/credits.jsp");
			        rd.forward(req, resp);
					break;
			}
		}
		
	}
}
