/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ClienteDAO;
import dao.ViajeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.ClienteVO;
import vo.ViajeVO;

/**
 *
 * @author Labing
 */
public class nuevoViajeServlet extends HttpServlet {
    private ViajeDAO viaje;
    private ClienteDAO cliente;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            /* TODO output your page here. You may use following sample code. */
            this.viaje = new ViajeDAO();
            this.cliente = new ClienteDAO();
            
            int cedula = Integer.parseInt(request.getParameter("cedula"));
            ClienteVO c = this.cliente.buscar(cedula);
            if (c == null) {
                request.setAttribute("existe", "error");
                request.getRequestDispatcher("nuevoViaje.jsp").forward(request, response);
            }
            
            String destino = request.getParameter("destino");
            int acompanantes = Integer.parseInt(request.getParameter("acompanantes"));
            String fecha = request.getParameter("fecha");
            
            ViajeVO via = new ViajeVO();
            via.setCedulaCliente(cedula);
            via.setDestino(destino);
            via.setAcompanantes(acompanantes);
            via.setFecha(fecha);
            
            boolean res = this.viaje.insertar(via);
            
            if (res) {
                request.setAttribute("mensaje", "error");
            }else{
                request.setAttribute("mensaje", "ok");
                if (via.getAcompanantes() == 0) {
                    ArrayList <ViajeVO> viajeros = this.cliente.listarTodo();
                    for (int i = 0; i < viajeros.size(); i++) {
                        if (viajeros.get(i).getAcompanantes() == 0 && viajeros.get(i).getDestino().equalsIgnoreCase(via.getDestino())) {
                            ClienteVO cl = this.cliente.buscar(viajeros.get(i).getCedulaCliente());
                            if (cl != null) {
                                request.setAttribute("match", "si");
                            }
                        }
                    }
                }
            }
            
            request.getRequestDispatcher("nuevoViaje.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
