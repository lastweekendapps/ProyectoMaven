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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;
import vo.ClienteVO;

/**
 *
 * @author ayoro
 */
public class ClienteDAO {
    private Connection conexion;

    public ClienteDAO() {
        this.conexion = Conexion.getConnection();
    }
    
    public boolean insertar(ClienteVO cliente) {
        boolean resultado = false;
        try {
            //1.Establecer la consulta
            String consulta = "INSERT INTO Clientes VALUES(?,?,?,?)";
            //2. Crear el PreparedStament
            PreparedStatement statement
                    = this.conexion.prepareStatement(consulta);
            //-----------------------------------
            statement.setInt(1, cliente.getCedula());
            statement.setString(2, cliente.getNombre());
            statement.setString(3, cliente.getEmail());
            statement.setInt(4, cliente.getTelefono());
            //--------------------------------------
            //3. Hacer la ejecucion
            System.out.println("metió");
            resultado = statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;
    }
    
    public ArrayList listarTodo(){
       //1.Consulta
       ArrayList respuesta = new ArrayList();
       String consulta ="SELECT * FROM Clientes";
        try {
            //----------------------------
            //Statement
            Statement statement =
                    this.conexion.createStatement();
            //Ejecucion
            ResultSet resultado = 
                    statement.executeQuery(consulta);
            //----------------------------
            //Recorrido sobre el resultado
            while(resultado.next()){
                int cedula = resultado.getInt("Cedula");
                respuesta.add(cedula);
                String nombre = resultado.getString("NombreCliente");
                respuesta.add(nombre);
                String email = resultado.getString("Email");
                respuesta.add(email);
                int telefono = resultado.getInt("Telefono");
                respuesta.add(telefono);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return respuesta;
    }
    
    public ClienteVO buscar(int codigo) {
        ClienteVO cliente = null;
        try {
            String consulta = "SELECT * FROM Clientes WHERE Cedula = ?";
            PreparedStatement statement
                    = this.conexion.prepareStatement(consulta);

            statement.setInt(1, codigo);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                cliente = new ClienteVO();
                cliente.setCedula(resultado.getInt("Cedula"));
                cliente.setNombre(resultado.getString("NombreCliente"));
                cliente.setEmail(resultado.getString("Email"));
                cliente.setTelefono(resultado.getInt("Telefono"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    
    public boolean editar(ClienteVO cliente) {
        boolean result = false;
        String query = "update Clientes set NombreCliente = ?, Email = ?, Telefono = ? where Cedula = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = this.conexion.prepareStatement(query);
            preparedStmt.setString(1, cliente.getNombre());
            preparedStmt.setString(2, cliente.getEmail());
            preparedStmt.setInt(3, cliente.getTelefono());
            preparedStmt.setInt(4, cliente.getCedula());
            if (preparedStmt.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public boolean borrar(ClienteVO cliente) {
        boolean result = false;
        String query = "delete from Clientes where Cedula = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = this.conexion.prepareStatement(query);
            preparedStmt.setInt(1, cliente.getCedula());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
