package com.ymens.spring.manager;

import com.ymens.hibernate.UserType;
import com.ymens.dao.CartDao;
import com.ymens.dao.LoginDao;
import com.ymens.servlet.PaginationServlet;
import com.ymens.spring.dao.RegisterDao;
import com.ymens.spring.interfaces.IUser;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static DataSource dataSource;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        WebApplicationContext ctx =
                WebApplicationContextUtils
                        .getWebApplicationContext(context);
         dataSource= (DataSource) ctx.getBean("dataSource");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String n = request.getParameter("name");
        String p = request.getParameter("password");
        HttpSession session = request.getSession(false);
        String realname = LoginDao.getRealName(n, p);
        IUser regDao = new RegisterDao();
        if (session != null) {
            session.setAttribute("name", n);
            session.setAttribute("password", p);
            session.setAttribute("realname", realname);
        }
        if (regDao.validateUser(n, p) == true) {
            String type = UserType.getType(n,p);
            if (type.equalsIgnoreCase("admin")) {
                session.setAttribute("null", "no");
                session.setAttribute("type", "admin");
                getServletContext().getRequestDispatcher("/selectbooksadminServlet").forward(request, response);

            }else if (type.equalsIgnoreCase("user")) {
                session.setAttribute("null", "no");
                session.setAttribute("type", "user");
                getServletContext().getRequestDispatcher("/selectbooksuserServlet").forward(request, response);
            }
        } else {
            out.print("<p style=\"color:red\">Sorry username or password error</p>");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.include(request, response);
        }
        ArrayList cartitems = CartDao.getCartItems();
        session.setAttribute("cart", cartitems);
        PaginationServlet ps = new PaginationServlet();
        ps.UpdateCurrentPage(1);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}