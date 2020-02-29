/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import model.Student;

/**
 *
 * @author rutil
 */
public class StudentDao {
	 private DbConnection conn;

	    public StudentDao(DbConnection conn) {
	        this.conn = conn;
	    }
	    
	    /**
	     * Metodo para validar un usuario alumno en la base de datos
	     * @param user Nombre de usuario
	     * @param pass Contrasenna
	     */
	    public Student login(String user, String pass) {

	        try {
	        	//Consulta SQL
	            String sql = "select * from students where username=? and passwd = ? limit 1";
	            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
	            preparedStatement.setString(1, user);
	            preparedStatement.setString(2, pass);
	            ResultSet rs = preparedStatement.executeQuery();
	            Student student = new Student();
	            //Leer el resultado
	            while (rs.next()) {
	                // Rellenar el objeto de tipo alumno
	            	student.setId(rs.getInt("id"));
	            	student.setUsername(rs.getString("username"));
	            	student.setPasswd(rs.getString("passwd"));
	            }
	            return student;
	            
	        } catch (SQLException e) {
	            System.out.println("Error UsuarioDao.login: " + e.getMessage());
	            return null;
	        }
	    }
	    
	    //----------------------------------------------------------------------
	    
	    public Student getById(int id) {
	    	try {
	            String sql = "select * from students where id=? limit 1";
	            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
	            preparedStatement.setInt(1, id); // Set idServidor
	            ResultSet rs = preparedStatement.executeQuery();
	            Student s = new Student();
	            while (rs.next()) {
	                // Rellenar el objeto con los datos
	            	s.setId(rs.getInt("id"));
	            	s.setName(rs.getString("name"));
	            	s.setSurname(rs.getString("surname"));
	            	s.setUsername(rs.getString("username"));
	            	s.setPasswd(rs.getString("passwd"));
	            	s.setBirthDate(rs.getDate("birth_date"));
	            	s.setAvailable(rs.getBoolean("available"));
	            	s.setPhone(rs.getInt("phone"));
	            	s.setEmail(rs.getString("email"));
            		s.setObservations(rs.getString("observations"));
            		s.setLinkedin(rs.getString("linkedin"));
            		s.setGithub(rs.getString("github"));
            	}
	            return s;
	        } catch (SQLException e) {            
	            System.out.println("Error ServidorDao.getById: " + e.getMessage());
	            return null;
	        }
	    }
	    
	    //----------------------------------------------------------------------
	    
	    public void update(int id, String name, String surname, String email, int phone, String linkedin, 
	    		String github, String observations, Date birth, boolean status) {
	    	try {
		    	String sql = "update students set name=?, surname=?, email=?, phone=?, linkedin=?, github=?, "
		    			+ "observations=?, birth_date=?, available=? where id=?";
		    	
		    	 PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
		         preparedStatement.setString(1, name);
		         preparedStatement.setString(2, surname);
		         preparedStatement.setString(3, email);
		         preparedStatement.setInt(4, phone);
		         preparedStatement.setString(5, linkedin);
		         preparedStatement.setString(6, github);
		         preparedStatement.setString(7, observations);
		         preparedStatement.setDate(8, new java.sql.Date(birth.getTime()));
		         preparedStatement.setBoolean(9, status);
		         preparedStatement.setInt(10, id);
		         //Ejecutar actualizacion
		         preparedStatement.executeUpdate();
		         preparedStatement.close();
		         
    		} catch (SQLException e) {
    			System.out.println("Error al actualizar " + e.getMessage());
    		} 
	    }
	    
	    //---------------------------------------------------------------------------
	    
	    public void remove(int id) {
	    	try {
	    		 String sql = "delete from students where id=?";
		    	 PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
		         preparedStatement.setInt(1, id);
		    } catch (SQLException e) {
				System.out.println("Error al actualizar " + e.getMessage());
			} 
	         
	    }
	    
	    //----------------------------------------------------------------------------
	    
	    public void add(String name, String surname, String username, String passwd) {
	    	try {
	    		 String sql = "insert into students (name, surname, username, passwd, birth_date, available, phone, email) values (?,?,?,?,?,?,?,?)";
		    	 PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
		         preparedStatement.setString(1, name);
		         preparedStatement.setString(2, surname);
		         preparedStatement.setString(3, username);
		         preparedStatement.setString(4, passwd);
		         preparedStatement.setDate(5, new java.sql.Date(new Date().getTime()));
		         preparedStatement.setBoolean(6, true);
		         preparedStatement.setInt(7, 0);
		         preparedStatement.setString(8, "email@email.com");
		         
		         //Ejecutar inserccion
		         preparedStatement.executeUpdate();
		         preparedStatement.close();
		    } catch (SQLException e) {
				System.out.println("Error al actualizar " + e.getMessage());
			} 
	         
	    }
}
