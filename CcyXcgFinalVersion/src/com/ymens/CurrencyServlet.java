package com.ymens;


import dao.SearchDao;
import dao.UserDao;

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
public class CurrencyServlet extends HttpServlet {
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
        UserDao user = new UserDao();
        String pairtosearch = user.currency;
        //list = SearchDao.searchPair(pairtosearch);
        int len = list.size();
        session = request.getSession(false);
        if(len >0) {
            session.setAttribute("search", list);
            session.setAttribute("lenght", len);
        } else if(len==0){
            session.setAttribute("lenght", len);
        }
        getServletContext().getRequestDispatcher("/Currency.jsp").forward(request, response);
    }

}


