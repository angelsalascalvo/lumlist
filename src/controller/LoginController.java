/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AdminDao;
import dao.DbConnection;
import dao.StudentDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import model.Student;

/////////////////// CONTROLADOR LOGIN DEL SITIO ////////////////////////
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * RECIBIR PETICIONES GET
	 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
		HttpSession session = req.getSession();
		RequestDispatcher rd;
		
		if(req.getParameter("action")==null) {
			 //Comprobar si esta registrado
			 if (session.getAttribute("admin") != null || session.getAttribute("student")!=null) {
				 resp.sendRedirect(req.getContextPath() + "/index");
			 }else {
				 rd = req.getRequestDispatcher("/login.jsp");
				 rd.forward(req, resp);    
			 }
		}else {
			//En funcion de la accion actuaremos
			switch(action) {
				case "logout":
					session.invalidate();
					resp.sendRedirect(req.getContextPath() + "/index");
					break;
			}
		}
    }
    
    //----------------------------------------------------------------------------------------
   
    /**
	 * RECIBIR PETICIONES POST
	 * COMPROBAR SI UN USUARIO Y CONTRASE�A CORRESPONDEN CON ALGUN
     * ADMINISTRADOR O ALUMNO DE LA BASE DE DATOS
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	DbConnection conn = new DbConnection();
    	RequestDispatcher rd;
        
        // Recibimos parametros del formulario de login
        String userParam = req.getParameter("user");
        String passParam = req.getParameter("pwd");
        
        // Recuperamos una instancia del objeto HttpSession
        HttpSession session = req.getSession();
        
        // 1. Comprobar si es un administrador    
        AdminDao adminDao = new AdminDao(conn);
        Admin admin = adminDao.login(userParam, passParam);

        if (admin.getId() > -1) {
            session.setAttribute("admin", admin);
            req.setAttribute("admin", admin);
            
            resp.sendRedirect(req.getContextPath() + "/?action=options");
            
        // 2. Comprobar si es un alumno
        } else {
            StudentDao studentDao = new StudentDao(conn);
            Student student = studentDao.login(userParam, passParam);

            if (student.getId() > -1) {
                session.setAttribute("student", student);
                resp.sendRedirect(req.getContextPath() + "/student?action=show&id="+student.getId());

            // Indicar error de login
            } else {
                req.setAttribute("error", "Usuario o contraseña incorrecta");
                rd = req.getRequestDispatcher("/login.jsp");
                rd.forward(req, resp);
            }
        }
        
         conn.disconnect();
    }
}