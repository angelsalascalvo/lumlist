package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Course;

public class StudentCourseDao {
	private DbConnection conn;

    public StudentCourseDao(DbConnection conn) {
        this.conn = conn;
    }
    
    //----------------------------------------------------------------
    
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
    
    //----------------------------------------------------------------
    
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
    
    
    //----------------------------------------------------------------
    
    /**
     * METODO PARA RECUPERAR UN LISTADO DE TODOS LOS CURSOS DE UN ALUMNO
     * @param id
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
