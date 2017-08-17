package com.ymens.servlet;

import com.ymens.dao.SelectBooksDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by madalina.luca on 8/10/2017.
 */
public class ProductsServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public static LinkedList list = new LinkedList();
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        list = SelectBooksDao.select();
        String url = "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
       dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        getServletContext().getRequestDispatcher("/productsServlet").forward(request, response);
    }
}
