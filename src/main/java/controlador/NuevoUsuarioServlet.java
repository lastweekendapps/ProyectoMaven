package controlador;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.UsuarioVO;
/**
 *
 * @author Nicolas
 */
//@WebServlet(name = "NuevoUsuarioServlet", urlPatterns = {"/NuevoUsuarioServlet"})
public class NuevoUsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        response.setContentType("text/html;charset=UTF-8");
        
            int id;

            try {

                id = Integer.parseInt(request.getParameter("id"));

            } catch (Exception e) {
                request.setAttribute("Mensaje1", "no es numero");
                id = 0;

            }
            if (!request.getParameter("psw").equals(request.getParameter("confPsw")) || id == 0) {
                request.setAttribute("Mensaje2", "contraseñas diferentes");
            } else {

                String name = request.getParameter("nombre");
                String password = request.getParameter("psw");
                UsuarioDAO dao = new UsuarioDAO();

                UsuarioVO user = new UsuarioVO();
                user.setId(id);
                user.setPassword(password);
                user.setUser(name);

                if (!dao.insertar(user)) {


                    request.setAttribute("Mensaje", "ok");

                } else {
                    request.setAttribute("Mensaje3", "Error");
                }

            }

            RequestDispatcher dispacher = request.getRequestDispatcher("nuevoUsuario.jsp");
            dispacher.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(NuevoUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(NuevoUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
