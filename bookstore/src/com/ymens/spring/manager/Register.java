package com.ymens.spring.manager;


import com.ymens.spring.beans.User;
import com.ymens.spring.dao.UserTypeDao;
import com.ymens.spring.interfaces.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Register extends HttpServlet {

 private static final long serialVersionUID = 1L;
        public static User user = new User();

    @Autowired
    IUser userdao;
    @Autowired
    UserTypeDao userTypeDao;

    public void init() {
    }
        public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            ServletContext context = getServletContext();
            WebApplicationContext ctx =
                    WebApplicationContextUtils
                            .getWebApplicationContext(context);
            userdao = (IUser) ctx.getBean("userDao");
            userTypeDao = (UserTypeDao) ctx.getBean("userTypeDao");
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            String n=request.getParameter("username");
            String p=request.getParameter("userpass");
            String e=request.getParameter("email");
            String rn=request.getParameter("realname");
            HttpSession session = request.getSession(false);
            Pagination ps = new Pagination();
            ps.UpdateCurrentPage(1);
            if(session!=null)
                session.setAttribute("name", n);
            user.setUsername(n);
            user.setPassword(p);
            user.setEmail(e);
            user.setRealName(rn);
            user.setType(userTypeDao.getIdUser());
            if(userdao.insertUser(user) == 1) {
                RequestDispatcher rd = request.getRequestDispatcher("products_admin.jsp");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                out.println("<font color=red>Please fill all the fields</font>");
                rd.include(request, response);
            }
            out.close();
        }
    }
