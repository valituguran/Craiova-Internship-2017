

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
        try {
            page= Integer.parseInt(request.getParameter("page"));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        HttpSession session = request.getSession(false);
        if (request.getParameter("button1") != null) {
            page = PaginationDao.add(page);
        }
        else if (request.getParameter("button2") != null) {
            page = PaginationDao.minus(page);
        }
        session.setAttribute("page", page);
        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
        out.close();
    }
}