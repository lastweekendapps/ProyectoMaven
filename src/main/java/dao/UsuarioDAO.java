/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;
import vo.UsuarioVO;

/**
 *
 * @author ayoro
 */
public class UsuarioDAO {

    private Connection conexion;

    public UsuarioDAO() {
        this.conexion = Conexion.getConnection();
    }

    public boolean insertar(UsuarioVO usuario) throws SQLException {
        boolean res = false;
        try{
        String query = "INSERT INTO Usuarios VALUES (?,?,?)";
        PreparedStatement preparedStmt = this.conexion.prepareStatement(query);

        preparedStmt.setInt(1, usuario.getId());
        preparedStmt.setString(2, usuario.getUser());
        preparedStmt.setString(3, usuario.getPassword());
        
        res = preparedStmt.execute();
        }catch(SQLException e){
            res = true;
        }
        System.out.println("reeeeeessssss" +res);
        return res;
    }


    public boolean editarPassword(UsuarioVO usuario) {
        boolean res = false;
        String query = "update Usuarios set Contrasena = ? where IdUsuario = ?";

        try {
            PreparedStatement preparedStmt = this.conexion.prepareStatement(query);

            preparedStmt.setString(1, usuario.getPassword());
            preparedStmt.setInt(2, usuario.getId());

            if (preparedStmt.executeUpdate() > 0) {
                res = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    public int borrar(int id, String user, String psw) {

        int i = 0;
        if (psw.equals("pass")) {
            String query = "delete from Usuarios where IdUsuario = ? and Usuario = ?";
            try {

                PreparedStatement ps = this.conexion.prepareStatement(query);
                ps.setInt(1, id);
                ps.setString(2, user);

                if (ps.executeUpdate() > 0) {
                    i = 2;
                } else {
                    i = 1;
                }

            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return i;
    }

    public boolean loggear(UsuarioVO usuario) {

        boolean res = false;
        String query = "select * from Usuarios where IdUsuario = ? and Usuario = ? and Contrasena = ?";

        try {
            PreparedStatement preparedStmt = this.conexion.prepareStatement(query);

            preparedStmt.setInt(1, usuario.getId());
            preparedStmt.setString(2, usuario.getUser());
            preparedStmt.setString(3, usuario.getPassword());
//            if (preparedStmt.executeQuery() != null) {

            ResultSet rs = preparedStmt.executeQuery();

            if (rs.next()) {
                res = true;
            }
//            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

}
