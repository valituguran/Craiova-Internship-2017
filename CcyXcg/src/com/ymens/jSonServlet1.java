package com.ymens;
import dao.ChartDao;
import dao.ParseDao;
import javafx.scene.chart.Chart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONArray;
//import org.json.JSONException;
import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Servlet implementation class JFreeChartServlet
 */
public class jSonServlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text");
        String pair=request.getParameter("param");
        int x= Integer.parseInt(request.getParameter("day1"));
        ParseDao.getcurrency();
        ChartDao.chartpair(pair);
        JSONObject jsonobject = new JSONObject();
        JSONArray  jsonArray = new JSONArray();

        for(int i = ChartDao.valuesearch.size(); i>=ChartDao.valuesearch.size()-x; i--){
            try {
                jsonobject=new JSONObject();
                jsonobject.put("val",ChartDao.valuesearch.get(i));
                jsonobject.put("Day",ChartDao.datasearch.get(i));
                jsonArray.put(jsonobject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(jsonArray.length()<2){
            response.getWriter().write("error");
            response.getWriter().write(jsonArray.toString());
            response.getWriter().write(jsonobject.toString());

        }
        else {
            response.getWriter().write(jsonArray.toString());

        }
    }

}


