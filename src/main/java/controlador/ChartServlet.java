/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ViajeDAO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class ChartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        JFreeChart chart = getChart();
        int width = 500;
        int height = 350;
        ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);

    }

    public JFreeChart getChart() {

        DefaultPieDataset dataset = new DefaultPieDataset();

        ViajeDAO v = new ViajeDAO();
        ArrayList ar = v.personasPorDestino();

        int arr[] = new int[ar.size() / 2];
        int total = 0;
        int cont = 0;

        for (int i = 0; i < ar.size(); i += 2) {
            int s = (Integer) ar.get(i + 1);
            arr[cont] = s;
            total += s;
            cont++;
        }

        int porcent[] = new int[arr.length];

        for (int i = 0; i < porcent.length; i++) {
            int valor = (arr[i] * 100 / total);
            porcent[i] = valor;
        }

        String strings[] = new String[arr.length];
        int cont2 = 0;
        for (int i = 0; i < ar.size(); i += 2) {
            String s = (String) ar.get(i);
            strings[cont2] = s;
            cont2++;
        }

        for (int i = 0; i < arr.length; i++) {
            dataset.setValue(strings[i], porcent[i]);
        }

        boolean legend = true;
        boolean tooltips = false;
        boolean urls = false;

        JFreeChart chart = ChartFactory.createPieChart("Primero", dataset, legend, tooltips, urls);

        chart.setBorderPaint(Color.GREEN);
        chart.setBorderStroke(new BasicStroke(5.0f));
        chart.setBorderVisible(true);

        return chart;

    }

}
