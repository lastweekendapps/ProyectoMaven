/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.ClienteDAO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ayoro
 */
public class PDFServlet extends HttpServlet {
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
            throws ServletException, IOException, BadElementException, DocumentException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            this.cliente = new ClienteDAO();
	    String path = (String) request.getAttribute("path");
            
            ArrayList c = cliente.listarTodo();
            
            FileOutputStream archivo = new FileOutputStream(path);
		Document documento = new Document();
		PdfWriter.getInstance(documento, archivo);
		documento.open();
		documento.add(new Paragraph("Clientes!"));
                documento.add(new Paragraph("Pruebita del primer pdf",
				FontFactory.getFont("arial",   // fuente
				22,                            // tamaño
				Font.ITALIC,                   // estilo
				BaseColor.CYAN)));             // color
                
                
                documento.add(new Paragraph("Nueva tablita",FontFactory.getFont("arial",28,Font.BOLD,BaseColor.BLUE)));
                documento.add( Chunk.NEWLINE );
                PdfPTable tabla = new PdfPTable(4);
                Paragraph ced = new Paragraph("Cédula",FontFactory.getFont("arial",12,Font.BOLD,BaseColor.BLACK));
                Paragraph nom = new Paragraph("Nombre",FontFactory.getFont("arial",12,Font.BOLD,BaseColor.BLACK));
                Paragraph cor = new Paragraph("Correo",FontFactory.getFont("arial",12,Font.BOLD,BaseColor.BLACK));
                Paragraph tel = new Paragraph("Teléfono",FontFactory.getFont("arial",12,Font.BOLD,BaseColor.BLACK));
                tabla.addCell(ced);
                tabla.addCell(nom);
                tabla.addCell(cor);
                tabla.addCell(tel);
                
                for (int i = 0; i < c.size(); i+=4) {
                        tabla.addCell(""+c.get(i));
                        tabla.addCell((String) c.get(i+1));
                        tabla.addCell((String) c.get(i+2));
                        tabla.addCell(""+c.get(i+3));
                }
                documento.add(tabla);
                documento.add(new Paragraph("Finito"));
        	documento.close();
                response.sendRedirect("opciones.jsp");
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
        } catch (DocumentException ex) {
            Logger.getLogger(PDFServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (DocumentException ex) {
            Logger.getLogger(PDFServlet.class.getName()).log(Level.SEVERE, null, ex);
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
