package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Course;

//Comunicaciones con la tabla administradores de la base de datos
public class CourseDao {
	private DbConnection conn;
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * CONSTRUCTOR PARAMETRIZADO
	 * @param conn
	 */
    public CourseDao(DbConnection conn) {
        this.conn = conn;
    }
    
    //------------------------------------------------------------------------------------------
    
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
    
    //------------------------------------------------------------------------------------------
    
    /**
     * METODO PARA OBTENER EL NOMBRE DE UN CURSO INDICADO POR ID
     * @param id
     * @return
     */
    public String getName(int id) {
    	try {
            String sql = "select name from courses where id=? limit 1";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id); // Set idServidor
            ResultSet rs = preparedStatement.executeQuery();
            String name="";
            while (rs.next()) {
                // Rellenar el objeto con los datos
            	name = rs.getString("name");
        	}
            return name;
        } catch (SQLException e) {            
            System.out.println("Error ServidorDao.getById: " + e.getMessage());
            return null;
        }
    }

    //------------------------------------------------------------------------------------------
    
    /**
     * METODO PARA ACTUALIZAR LOS DATOS DE UN CURSO
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

    //------------------------------------------------------------------------------------------
    
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
    
    //------------------------------------------------------------------------------------------
    
    /**
     * METODO PARA AGREGAR UN CURSO A LA BASE DE DATOS
     * @param name
     */
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