package com.ymens.spring.manager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {

        response.setContentType("text/html");

        HttpSession session = request.getSession();
        session.invalidate();
        Pagination ps = new Pagination();
        ps.UpdateCurrentPage(1);
        request.getRequestDispatcher("index.jsp").include(request, response);
    }
}

