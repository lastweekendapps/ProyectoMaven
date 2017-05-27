/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ayoro
 */
public class CorreoServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try {
                
                String usuario = request.getParameter("usuario");
                String pass = request.getParameter("password");
                String destinatario = request.getParameter("destinatario");
                String asunto = request.getParameter("asunto");
                String mensaje = request.getParameter("mensaje");

                // Propiedades de la conexión, configuracion del servicio smtp.
                Properties props = new Properties();
                props.setProperty("mail.smtp.host", "smtp.gmail.com");
                props.setProperty("mail.smtp.starttls.enable", "true");
                props.setProperty("mail.smtp.port", "587");
                props.setProperty("mail.smtp.user", usuario+"@gmail.com");
                props.setProperty("mail.smtp.auth", "true");

                // Preparamos la sesion
                Session session = Session.getDefaultInstance(props);

                // Construimos el mensaje
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(usuario+"@gmail.com"));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario+"@gmail.com"));
                message.setSubject(asunto);
                message.setText(mensaje);

                // Lo enviamos.
                Transport t = session.getTransport("smtp");
                t.connect(usuario+"@gmail.com", pass);
                t.sendMessage(message, message.getAllRecipients());

                // Cierre.
                t.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
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
