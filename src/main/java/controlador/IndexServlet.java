/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vo.UsuarioVO;

/**
 *
 * @author ayoro
 */
public class IndexServlet extends HttpServlet {

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
            int id;

            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (Exception e) {

                request.setAttribute("Mensaje1", "no es numero");

                id = 0;

            }
            
            HttpSession session = request.getSession();
            UsuarioVO user = (UsuarioVO) session.getAttribute("user");
            
            if(user == null){
                
                user = new UsuarioVO();
                session.setAttribute("user", user);
                
            }
            String name = request.getParameter("usuario");
            String password = request.getParameter("contrasena");
            user.setId(id);
            user.setUser(name);
            user.setPassword(password);
            session.setAttribute("user", user);
            session.setAttribute("nombre", user.getUser());
            UsuarioDAO dao = new UsuarioDAO();

            if (!dao.loggear(user)|| id==0) {
                System.out.println("No se pudo loggear");
                request.setAttribute("Mensaje", "error");

            } else {

                System.out.println("OOOOKKKKKKKKKKKK");
                request.setAttribute("Mensaje", "ok");
                
                

            }

            RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
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
