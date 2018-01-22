

package com.ymens;

import com.ymens.myAccountDao;
import dao.ChartDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class MyAccountServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String n=request.getParameter("name");
        String p=request.getParameter("password");
        String currency= request.getParameter("currency");
        String username = request.getParameter("username");
        HttpSession session = request.getSession(false);
        if(session!=null)
            session.setAttribute("name", n);
        session.setAttribute("currency",currency);

        if(com.ymens.LogInDao.validate(n,p,1)==true){
            response.sendRedirect("admin.jsp");
        }
        else if(com.ymens.LogInDao.validate(n,p,0)==true){
            response.sendRedirect("homeuser.jsp");
        }
        else{
            out.write("Username or password incorect!");
        }

        out.close();
    }
}