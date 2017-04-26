/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.ClienteVO;

/**
 *
 * @author ayoro
 */
public class EditarClienteServlet extends HttpServlet {
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
            this.cliente = new ClienteDAO();
            String ced = request.getParameter("cedula");
            if (ced == "") {
                System.out.println("ENTROOOO");
                int cedula = Integer.parseInt(request.getParameter("busqueda"));
                ClienteVO clien = this.cliente.buscar(cedula);

                request.setAttribute("cedula", Integer.toString(clien.getCedula()));
                request.setAttribute("nombre", clien.getNombre());
                request.setAttribute("email", clien.getEmail());
                request.setAttribute("telefono", Integer.toString(clien.getTelefono()));

                request.getRequestDispatcher("editarCliente.jsp").forward(request, response);
            } else {
                int cc = Integer.parseInt(request.getParameter("cedula"));
                String nombreNuevo = request.getParameter("nombre");
                String emailNuevo = request.getParameter("email");
                int telefonoNuevo = Integer.parseInt(request.getParameter("telefono"));
                
                ClienteVO clien = new ClienteVO();
                clien.setCedula(cc);
                clien.setNombre(nombreNuevo);
                clien.setEmail(emailNuevo);
                clien.setTelefono(telefonoNuevo);
                
                boolean res = this.cliente.editar(clien);
                
                if (res) {
                    request.setAttribute("mensaje", "ok");
                }else{
                    request.setAttribute("mensaje", "error");
                }
                
                request.getRequestDispatcher("buscarCliente.jsp").forward(request, response);
            }
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
