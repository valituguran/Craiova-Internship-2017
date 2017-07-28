package com.ymens.servlet;


import com.ymens.dao.RegisterDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by madalina.luca on 7/27/2017.
 */
public class RegisterServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String n=request.getParameter("username");
        String p=request.getParameter("userpass");
        String e=request.getParameter("email");
        HttpSession session = request.getSession(false);
        if(session!=null)
            session.setAttribute("name", n);

        if(RegisterDao.validate(n, p, e) > 0 ){
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
        }
        else{
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            out.println("<font color=red>Please fill all the fields</font>");
            rd.include(request, response);
        }

        out.close();
    }
}
