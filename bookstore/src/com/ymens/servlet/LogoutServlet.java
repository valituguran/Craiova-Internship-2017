package com.ymens.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by madalina.luca on 8/1/2017.
 */

    public class LogoutServlet extends HttpServlet{

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException,IOException{

            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            HttpSession session = request.getSession();
            //session.removeAttribute("name");
            //session.removeAttribute("password");
            //session.removeAttribute("realname");
            session.invalidate();
            PaginationServlet ps = new PaginationServlet();
            ps.UpdateCurrentPage(1);
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
    }

