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
public class ServletBB extends HttpServlet {
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
            
            if (request.getParameter("operacion").equals("Buscar")) {
                
                int cedula = Integer.parseInt(request.getParameter("busqueda"));

                ClienteVO c = this.cliente.buscar(cedula);
                String nombre = "";
                String correo = "";
                int telefono = 0;
                String tel = "";

                if (c != null) {
                    request.setAttribute("cedula", Integer.toString(c.getCedula()));
                    request.setAttribute("nombre", c.getNombre());
                    request.setAttribute("email", c.getEmail());
                    request.setAttribute("telefono", Integer.toString(c.getTelefono()));
                    request.getRequestDispatcher("buscarCliente.jsp").forward(request, response);
                }

                

            }else if (request.getParameter("operacion").equals("Borrar")){
                int cedula = Integer.parseInt(request.getParameter("cedula"));
                String nombre = request.getParameter("nombre");
                String correo = request.getParameter("email");
                int telefono = Integer.parseInt(request.getParameter("telefono"));
                
                ClienteVO cl = new ClienteVO();
                cl.setCedula(cedula);
                cl.setNombre(nombre);
                cl.setEmail(correo);
                cl.setTelefono(telefono);
                
                boolean borrar = this.cliente.borrar(cl);
                if (borrar) {
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
