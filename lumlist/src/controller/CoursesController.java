package controller;

import java.io.IOException;
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
import model.Course;


@WebServlet("/courses")
public class CoursesController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		RequestDispatcher rd;
		DbConnection conn = new DbConnection();
				
		CourseDao courseDao = new CourseDao(conn);
        List<Course> list = courseDao.getAll();
        // Compartimos la variable lista, para poder accederla desde la vista
        req.setAttribute("courses", list);
        System.out.println(list.size());
        
        conn.disconnect();
        
        /// Enviar informacion sobre logueo ///
		if(session.getAttribute("admin") != null)
			req.setAttribute("user", "admin");
		else if(session.getAttribute("student") != null)
			req.setAttribute("user", "student");
		//////////////////////////////////
        
        //Mostrar la vista 
		rd = req.getRequestDispatcher("/courses.jsp");
        rd.forward(req, resp);
        
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		RequestDispatcher rd;
		DbConnection conn;
		
		//En funcion de la accion actuaremos
		switch(action) {
			case "remove":
				int idRemove = Integer.parseInt(req.getParameter("id"));
				//Obtener datos del alumno
				conn = new DbConnection();
				CourseDao courseDaoRemove = new CourseDao(conn);
				courseDaoRemove.remove(idRemove);
				//Recargar esta página
				 doGet(req, resp);
				break;
				
			case "update":
				 int sId = Integer.parseInt(req.getParameter("id"));
				 String sName = req.getParameter("name");
				 //Llamada al metodo de actualizar
				 conn = new DbConnection();
				 CourseDao courseDao = new CourseDao(conn);
				 courseDao.update(sId, sName);
				 
				 //Recargar esta página
				 doGet(req, resp);
				 break;
				 
			case "store":
				 String sNameAdd = req.getParameter("name");				 
				 //Llamada al metodo de insertar
				 conn = new DbConnection();
				 CourseDao courseDaoInsert = new CourseDao(conn);
				 courseDaoInsert.add(sNameAdd);
				 conn.disconnect();
				 //Recargar esta página
				 doGet(req, resp);
				 
				break;
		}
	}
}
