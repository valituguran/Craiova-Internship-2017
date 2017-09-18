package com.ymens;
import dao.ParseDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class ParseHistoryServlet extends HttpServlet {
    public static  LinkedList pairs = new LinkedList();
    public static  LinkedList values = new LinkedList();
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int i = 0;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        String a = "E:\\workspace\\Craiova-Internship-2017\\parse\\src\\parse.xml";
        for (i = 0; i < Parse.values(a).size(); i++) {
            dao.ParseHistoryDao.addcurrency(ParsePairs.pairs(a).get(i), Parse.values(a).get(i));
        }

        ParseDao.getcurrency();
        pairs = ParseDao.pairs;
        values = ParseDao.values;
        session.setAttribute("pairs", pairs);
        session.setAttribute("values", values);
        response.sendRedirect("home.jsp");

        out.close();
    }

}