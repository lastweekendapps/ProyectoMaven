/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ClienteDAO;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class HSSFCreate extends HttpServlet {
    
    private ClienteDAO dao;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);  
    }

    public void destroy() {
    }

    /** Processes requests for both HTTP GET and POST methods.
     * @param request servlet request
     * @param response servlet response
     */

    protected void processRequest(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/vnd.ms-excel");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("new sheet");
        this.dao = new ClienteDAO();
        
        ArrayList registros = this.dao.listarTodo();
       
        Map<String, Object[]> data = new HashMap<String, Object[]>(); //Crea un hashMap
        
        data.put("1", new Object[] {"Cedula", "Nombre", "Correo","Telefomo"}); //Pasa el id de cada fila de HashMap
	int contador = 2;
        
        for (int i = 0; i < registros.size(); i+=4) {
            String cont = ""+contador;
            data.put(cont, new Object[] {registros.get(i), registros.get(i+1), registros.get(i+2), registros.get(i+3)});// con su respectivo registro
            contador++;
        }
                
                
//		data.put("3", new Object[] {2d, "Sam", 800000d});
//		data.put("4", new Object[] {3d, "Dean", 700000d});
		
                
                Set<String> keyset = data.keySet(); //Recibe el id de el HashMap
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object [] objArr = data.get(key); //Recibe todos los registros de cada key
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof Date) 
					cell.setCellValue((Date)obj);
				else if(obj instanceof Boolean)
					cell.setCellValue((Boolean)obj);
				else if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Double)
					cell.setCellValue((Double)obj);
			}
		}
        
        // Write the output 
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.close();
    }

    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */

    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Handles the HTTP POST method.
     * @param request servlet request
     * @param response servlet response
     */

    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Returns a short description of the servlet.
     */

    public String getServletInfo() {
       return "Example to create a workbook in a servlet using HSSF";
    }
}
