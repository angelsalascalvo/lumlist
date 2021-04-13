/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Admin;

//Comunicaciones con la tabla administradores de la base de datos

public class AdminDao {
    private DbConnection conn;
    
    //------------------------------------------------------------------------------------------

    /**
     * CONSTRUCTOR PARAMETRIZADO
     * @param conn
     */
    public AdminDao(DbConnection conn) {
        this.conn = conn;
    }
    
    //------------------------------------------------------------------------------------------
    
    /**
     * METODO PARA VALIDAR UN USUARIO ADMINISTRADOR EN LA BASE DE DATOS (LOGUEARSE)
     * @param user Nombre de usuario
     * @param pass Contrasenna
     */
    public Admin login(String user, String pass) {
        try {
        	//Consulta SQL
            String sql = "select * from admin where username=? and passwd = ? limit 1";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            ResultSet rs = preparedStatement.executeQuery();
            Admin admin = new Admin();
            //Leer el resultado
            while (rs.next()) {
                // Rellenar el objeto de tipo administrador
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPasswd(rs.getString("passwd"));
            }
            return admin;
            
        } catch (SQLException e) {
            System.out.println("Error UsuarioDao.login: " + e.getMessage());
            return null;
        }
    }
}