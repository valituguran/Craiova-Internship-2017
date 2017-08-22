package com.ymens.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            request.getRequestDispatcher("index.jsp").include(request, response);
            out.print("you are successfully logged out!");
        }
    }

