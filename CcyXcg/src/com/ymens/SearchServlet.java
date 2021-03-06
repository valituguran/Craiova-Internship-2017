package com.ymens;


import dao.SearchDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Created by madalina.luca on 8/1/2017.
 */
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LinkedList list = new LinkedList();
    HttpSession session;


    @Override
    public void init()
            throws ServletException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String pairtosearch = request.getParameter("search");
        SearchDao.searchPair(pairtosearch);
        int len = SearchDao.list1.size();
        session = request.getSession(false);
        if(len >0) {
            session.setAttribute("lenght", len);
        } else if(len==0){
            session.setAttribute("lenght", len);
        }
        response.sendRedirect("index.jsp");
    }

}


