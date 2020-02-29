package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.Course;

public class CourseDao {
	private DbConnection conn;

    public CourseDao(DbConnection conn) {
        this.conn = conn;
    }
    
    /**
     * METODO PARA OBTENER UN LISTADO DE TODOS LOS CURSOS DE LA BASE DE DATOS
     * @return
     */
    public List<Course> getAll(){
    	 try {
             String sql = "select * from courses";
             PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery();
             List<Course> list = new LinkedList<>();
             Course cour;
             while (rs.next()) {
            	 cour = new Course();
            	 cour.setName(rs.getString("name"));
            	 cour.setId(rs.getInt("id"));
                 // Agregar el curso a la lista
                 list.add(cour);
             }
             return list;

         } catch (SQLException e) {            
             System.out.println("Error ServidorDao.getAll: " + e.getMessage());
             return null;
         }
    }
    
    //-------------------------------------------------------------------------
    
    /**
     * METODO PARA ACTUALIZAR UN CURSO
     * @param id
     * @param name
     */
    public void update(int id, String name) {
    	try {
	    	String sql = "update courses set name=? where id=?";
	    	
	    	 PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
	         preparedStatement.setString(1, name);
	         preparedStatement.setInt(2, id);
	         //Ejecutar actualizacion
	         preparedStatement.executeUpdate();
	         preparedStatement.close();
	         
		} catch (SQLException e) {
			System.out.println("Error al actualizar " + e.getMessage());
		} 
    
    }
    
    //---------------------------------------------------------------------------
    
    /**
     * METODO PARA ELIMINAR UN CURSO DE LA BASE DE DATOS
     * @param id
     */
    public void remove(int id) {
    	try {
    		 String sql = "delete from courses where id=?";
	    	 PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
	         preparedStatement.setInt(1, id);
	         //Ejecutar borrado
	         preparedStatement.executeUpdate();
	         preparedStatement.close();
	    } catch (SQLException e) {
			System.out.println("Error al actualizar " + e.getMessage());
		} 
         
    }
    
  //----------------------------------------------------------------------------
    
    public void add(String name) {
    	try {
    		 String sql = "insert into courses (name) values (?)";
	    	 PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
	         preparedStatement.setString(1, name);
	         //Ejecutar inserccion
	         preparedStatement.executeUpdate();
	         preparedStatement.close();
	    } catch (SQLException e) {
			System.out.println("Error al actualizar " + e.getMessage());
		} 
         
    }
}
