/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.RecordatorioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vo.RecordatorioVO;
import vo.UsuarioVO;

/**
 *
 * @author ayoro
 */
public class RecordatorioServlet extends HttpServlet {
    private RecordatorioDAO recordatorio;
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
            this.recordatorio = new RecordatorioDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            String fecha = request.getParameter("date");
            String hora = request.getParameter("hour");
            String descripcion = request.getParameter("descr");
            HttpSession sesion = request.getSession();
            UsuarioVO user = (UsuarioVO) sesion.getAttribute("user");
            int idUsuario = user.getId();
            
            RecordatorioVO recor = new RecordatorioVO();
            recor.setIdRecordatorio(id);
            recor.setDescripcion(descripcion);
            recor.setFechaRecordatorio(fecha);
            recor.setHora(hora);
            recor.setIdUsuario(idUsuario);
            
            boolean inserto = this.recordatorio.insertar(recor);
            if(!inserto){
                request.setAttribute("mensaje","ok");
                request.setAttribute("id",idUsuario);
            }else{
                request.setAttribute("mensaje","error");
            }
            request.getRequestDispatcher("nuevoRecordatorio.jsp").forward(request,response);
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
