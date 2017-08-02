package com.ymens.servlet;

import com.ymens.dao.SelectBooks;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by madalina.luca on 8/1/2017.
 */
public class SelectBooksServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public LinkedList list = new LinkedList();
  @Override
   public void init() throws ServletException {

      list = SelectBooks.select();



   }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            response.setContentType("text/html");
        list = SelectBooks.select();
        HttpSession session = request.getSession(false);
        if (session != null)
            session.setAttribute("list", list);

           // response.sendRedirect("products.jsp");
       // request.getRequestDispatcher("products.jsp").include(request, response);
        getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);

        }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);

    }





}
