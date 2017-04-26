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
public class CoincidenciasServlet extends HttpServlet {
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
            String destino = request.getParameter("destino");
            ArrayList <ViajeVO> viajes = this.viaje.getMatch(destino);
            ArrayList clientes = new ArrayList();
            for (int i = 0; i < viajes.size(); i++) {
                int cedula = viajes.get(i).getCedulaCliente();
                ClienteVO c = this.cliente.buscar(cedula);
                if (c != null) {
                    clientes.add(cedula);
                    String nombre = c.getNombre();
                    clientes.add(nombre);
                    String correo = c.getEmail();
                    clientes.add(correo);
                    int telefono = c.getTelefono();
                    clientes.add(telefono);
                    
                }else{
                    request.setAttribute("mensaje", "error");
                }
            }
            
            if (clientes.size() > 0) {
                request.setAttribute("lista", clientes);
                request.setAttribute("mensaje", "ok");
            }else{
                request.setAttribute("mensaje", "sinCoincidencias");
            }
            request.getRequestDispatcher("coincidencias.jsp").forward(request, response);
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
