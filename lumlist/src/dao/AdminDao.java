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

/**
 *
 * @author rutil
 */
public class AdminDao {
    private DbConnection conn;

    public AdminDao(DbConnection conn) {
        this.conn = conn;
    }
    
    /**
     * Metodo para validar un usuario administrador en la base de datos
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
