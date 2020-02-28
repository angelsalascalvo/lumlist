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

/**
 *
 * @author rutil
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
        rd.forward(req, resp);
    }
   
    /**
     * METODO PARA COMPROBAR SI UN USUARIO Y CONTRASEÑA CORRESPONDEN CON ALGUN
     * ADMINISTRADOR O ALUMNO DE LA BASE DE DATOS
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
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
            session.setAttribute("user", admin);
            conn.disconnect();
            req.setAttribute("admin", admin);
            rd = req.getRequestDispatcher("/options.jsp");
            rd.forward(req, response);
            
        // 2. Comprobar si es un alumno
        } else {
        	StudentDao studentDao = new StudentDao(conn);
        	Student student = studentDao.login(userParam, passParam);
            conn.disconnect();

        	if (student.getId() > -1) {
                session.setAttribute("user", student);
                //rd = request.getRequestDispatcher("/admin.jsp");
                //rd.forward(request, response);
                
            // Indicar error de login
            } else {
            	req.setAttribute("error", "Usuario o contraseña incorrecta");
                rd = req.getRequestDispatcher("/login.jsp");
                rd.forward(req, response);
            }
        }
    }
}