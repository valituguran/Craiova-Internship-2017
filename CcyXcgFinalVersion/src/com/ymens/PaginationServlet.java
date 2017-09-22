

package com.ymens;

import dao.PaginationDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class PaginationServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int page = 1;
        int pageaccount=1;
        int pagecurrency=1;
        int pageindex=1;
        try {
            page= Integer.parseInt(request.getParameter("page"));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        HttpSession session = request.getSession(false);
        if (request.getParameter("button1") != null) {
            page = PaginationDao.add(page);
            response.sendRedirect("home.jsp");
        }
        else if (request.getParameter("button2") != null) {
            page = PaginationDao.minus(page);
            response.sendRedirect("home.jsp");
        }
        if (request.getParameter("jump") != null) {
            page = Integer.parseInt(request.getParameter("jump"));
            response.sendRedirect("home.jsp");
        }
        if (request.getParameter("buttonCurrency1") != null) {
            page = PaginationDao.addCurrency(page);
            response.sendRedirect("Currency.jsp");
        }
        else if (request.getParameter("buttonCurrency2") != null) {
            page = PaginationDao.minusCurrency(page);
            response.sendRedirect("Currency.jsp");
        }
        if (request.getParameter("buttonTransactions1") != null) {
            page = PaginationDao.addTransaction(page);
            response.sendRedirect("myAccount.jsp");
        }
        else if (request.getParameter("buttonTransactions2") != null) {
            page = PaginationDao.minusTransaction(page);
            response.sendRedirect("myAccount.jsp");
        }
        if (request.getParameter("buttonIndex1") != null) {
            page = PaginationDao.addIndex(page);
            response.sendRedirect("index.jsp");
        }
        else if (request.getParameter("buttonIndex2") != null) {
            page = PaginationDao.minusIndex(page);
            response.sendRedirect("index.jsp");
        }
        session.setAttribute("page", page);


        out.close();
    }
}