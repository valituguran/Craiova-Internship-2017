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
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by madalina.luca on 7/27/2017.
 */
public class RegisterServlet extends HttpServlet{
     private static final long serialVersionUID = 1L;

     public static int type = 0;

     public void init() throws ServletException {
         super.init();

         Connection conn = RegisterDao.connect();

         try {
             type = Integer.parseInt(String.valueOf(conn.prepareStatement("select id from user_type where name=user ")));

         } catch (SQLException e) {
             e.printStackTrace();
         }
         finally {
             try {
                 RegisterDao.closeConnection(conn);
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }

     }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String n=request.getParameter("username");
        String p=request.getParameter("userpass");
        String e=request.getParameter("email");
        String rn=request.getParameter("realname");

        HttpSession session = request.getSession(false);
        if(session!=null)
            session.setAttribute("name", n);

        if(RegisterDao.validate(n, p, rn , e) >0 ){
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
