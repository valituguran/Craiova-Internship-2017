

package com.ymens;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String n=request.getParameter("name");
        String p=request.getParameter("password");
        String user=request.getParameter("username");
        String email=request.getParameter("email");
        Double balance= Double.valueOf(request.getParameter("balance"));
        String currency =request.getParameter("currency");
        int type =0;


        HttpSession session = request.getSession(false);
        if(session!=null)
            session.setAttribute("name", n);
        session.setAttribute("balance",balance);
        session.setAttribute("currency",currency);

        if(com.ymens.RegisterDao.adduser(n,p,user,email,type,balance,currency) ==1){
            out.print("<p style=\"color:red\">You are now register</p>");
            request.getSession().invalidate();
            response.sendRedirect("login.jsp");
            return;
        }
        else if(n.length()<3 && p.length()<3){
            out.print("<p style=\"color:red\">Your password or username are not allowed</p>");
            response.sendRedirect("register.jsp");
        }

        out.close();
    }
}