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
public class NuevoClienteServlet extends HttpServlet {
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
            int cedula = 0;
            try {
                cedula = Integer.parseInt(request.getParameter("cedula"));
            } catch (Exception e) {
                request.setAttribute("mensaje", "numero");
                request.getRequestDispatcher("nuevoCliente.jsp").forward(request, response);
            }
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            int telefono = 0;
            try {
                telefono = Integer.parseInt(request.getParameter("telefono"));
            } catch (Exception e) {
                request.setAttribute("mensaje", "numero");
                request.getRequestDispatcher("nuevoCliente.jsp").forward(request, response);
            }
            
            
            if (cedula != 0 && telefono != 0) {
            
                ClienteVO cvo = new ClienteVO();
                cvo.setCedula(cedula);
                cvo.setNombre(nombre);
                cvo.setEmail(email);
                cvo.setTelefono(telefono);

                boolean inserta = this.cliente.insertar(cvo);

                if (inserta) {
                    request.setAttribute("mensaje", "error");
                }else{
                    request.setAttribute("mensaje", "ok");
                }
            }else{
                request.setAttribute("mensaje", "numero");
            }
            request.getRequestDispatcher("nuevoCliente.jsp").forward(request, response);
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
