package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

//Comunicaciones con la tabla intermedia entre estudiantes y cursos de la base de datos
public class StudentCourseDao {
	private DbConnection conn;

	//------------------------------------------------------------------------------------------
	
	/**
	 * CONSTRUCTOR PARAMETRIZADO
	 * @param conn
	 */
    public StudentCourseDao(DbConnection conn) {
        this.conn = conn;
    }
    
    //------------------------------------------------------------------------------------------
    
    /**
     * METODO PARA AGREGAR UNA NUEVA ENTRADA EN LA BASE DE DATOS PARA RELACIONAR UN ALUMNO Y UN CURSO
     * @param idS
     * @param idC
     */
    public void add(int idS, int idC) {
    	try {
   		 	 String sql = "insert into student_course (id_student, id_course) values (?,?)";
	    	 PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
	         preparedStatement.setInt(1, idS);
	         preparedStatement.setInt(2, idC);
	         //Ejecutar inserccion
	         preparedStatement.executeUpdate();
	         preparedStatement.close();
	    } catch (SQLException e) {
			System.out.println("Error al actualizar " + e.getMessage());
		} 
    }
    
    //------------------------------------------------------------------------------------------
    
    /**
     * METODO PARA ELIMINAR TODAS LAS RELACIONES CON CURSOS DE UN DETERMINADO ALUMNO
     * @param id ID del alumno
     */
    public void removeAllStudent(int id) {
    	try {
   		 	 String sql = "delete from student_course where id_student=?";
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
     * METODO PARA RECUPERAR UN LISTADO DE TODOS LOS CURSOS DE UN ALUMNO
     * @param id del alumno
     * @return
     */
    public List<Integer> getCoursesStudent(int id) {
    	try {
            String sql = "select * from student_course where id_student=?";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            List<Integer> list = new LinkedList<>();
            while (rs.next()) {
            	//Agregar a la lista
                list.add(rs.getInt("id_course"));
            }
            return list;
        } catch (SQLException e) {            
            System.out.println("Error ServidorDao.getAll: " + e.getMessage());
            return null;
        }
    }
}
