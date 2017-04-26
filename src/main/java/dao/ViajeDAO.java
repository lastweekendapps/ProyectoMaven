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
import vo.ViajeVO;

/**
 *
 * @author Labing
 */
public class ViajeDAO {
    private Connection conexion;

    public ViajeDAO() {
        this.conexion = Conexion.getConnection();
    }
    
    public ArrayList <ViajeVO> listarTodo(){
       //1.Consulta
       ArrayList <ViajeVO> respuesta = new ArrayList();
       String consulta ="SELECT * FROM Viajes";
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
//                respuesta.add(cedula);
                String destino = resultado.getString("Destino");
//                respuesta.add(destino);
                int cantidadPersonas = resultado.getInt("CantidadPersonas");
//                respuesta.add(cantidadPersonas);
                String fecha = resultado.getString("Fecha");
//                respuesta.add(fecha);

                ViajeVO v = new ViajeVO();
                v.setCedulaCliente(cedula);
                v.setDestino(destino);
                v.setAcompanantes(cantidadPersonas);
                v.setFecha(fecha);
                respuesta.add(v);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return respuesta;
    }

    public boolean insertar(ViajeVO viaje) {
        boolean res = false;
        try{
        String query = "INSERT INTO Viajes VALUES (?,?,?,?)";
        PreparedStatement preparedStmt = this.conexion.prepareStatement(query);

        preparedStmt.setInt(1, viaje.getCedulaCliente());
        preparedStmt.setString(2, viaje.getDestino());
        preparedStmt.setInt(3, viaje.getAcompanantes());
        preparedStmt.setString(4, viaje.getFecha());
        
        res = preparedStmt.execute();
        }catch(SQLException e){
            res = true;
        }
        return res;
    }


    public boolean editar(ViajeVO viaje) {
        boolean res = false;
        String query = "update Viajes set Destino = ?, CantidadPersonas = ?, Fecha = ? where Cedula = ?";

        try {
            PreparedStatement preparedStmt = this.conexion.prepareStatement(query);

            preparedStmt.setString(1, viaje.getDestino());
            preparedStmt.setInt(2, viaje.getAcompanantes());
            preparedStmt.setString(3, viaje.getFecha());
            preparedStmt.setInt(4, viaje.getCedulaCliente());
            
            

            if (preparedStmt.executeUpdate() > 0) {
                res = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViajeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }
    
    public ViajeVO buscar(int codigo) {
        ViajeVO viaje = null;
        try {
            String consulta = "SELECT * FROM Viajes WHERE Cedula = ?";
            PreparedStatement statement
                    = this.conexion.prepareStatement(consulta);

            statement.setInt(1, codigo);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                viaje = new ViajeVO();
                viaje.setCedulaCliente(resultado.getInt("Cedula"));
                viaje.setDestino(resultado.getString("Destino"));
                viaje.setAcompanantes(resultado.getInt("CantidadPersonas"));
                viaje.setFecha(resultado.getString("Fecha"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return viaje;
    }
    
    public boolean borrar(ViajeVO viaje) {
        boolean result = false;
        String query = "delete from Viajes where Cedula = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = this.conexion.prepareStatement(query);
            preparedStmt.setInt(1, viaje.getCedulaCliente());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public ArrayList <ViajeVO> getMatch(String destino){
        ArrayList <ViajeVO> viajes = new ArrayList();
        String query = "select * from Viajes where Destino = "+destino+" and CantidadPersonas = 0";
        //Statement
            Statement statement;
        try {
            statement = this.conexion.createStatement();
            //Ejecucion
            ResultSet resultado = statement.executeQuery(query);
            
            while (resultado.next()) {
                int cedula = resultado.getInt("Cedula");
                String des = resultado.getString("Destino");
                int cantidadP = resultado.getInt("CantidadPersonas");
                String fecha = resultado.getString("Fecha");
                
                ViajeVO v = new ViajeVO();
                v.setCedulaCliente(cedula);
                v.setDestino(destino);
                v.setAcompanantes(cantidadP);
                v.setFecha(fecha);
                viajes.add(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ViajeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return viajes;
    }
}
