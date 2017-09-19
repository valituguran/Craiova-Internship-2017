package com.ymens.spring.manager;

import com.ymens.spring.dao.*;
import com.ymens.spring.interfaces.IAuthor;
import com.ymens.spring.interfaces.IBook;
import com.ymens.spring.interfaces.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LogIn extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Autowired
    IBook bookDao ;
    @Autowired
    IAuthor authorDao ;
    @Autowired
    IUser userDao ;
    @Autowired
    UserTypeDao userTypeDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    CartItemDao cartItemDao;
    private String ses;
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ServletContext context = getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(context);
        ServletContext service = conf.getServletContext();
        bookDao= (IBook) ctx.getBean("booksDao");
        authorDao = (IAuthor) ctx.getBean("authorsDao");
        userDao = (IUser) ctx.getBean("userDao");
        userTypeDao = (UserTypeDao) ctx.getBean("userTypeDao");
        orderDao = (OrderDao) ctx.getBean("orderDao");
        orderItemDao = (OrderItemDao) ctx.getBean("orderItemDao");
        cartItemDao = (CartItemDao) ctx.getBean("cartItemDao");
        ses = conf.getInitParameter("name");
        if (ses == null) {
            System.out.println("error");
        }
        ses = (String) service.getAttribute("name");
        if (ses == null){
            System.out.println("error");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String n = request.getParameter("name");
        String p = request.getParameter("password");
        HttpSession session = request.getSession(false);
        String realname = userDao.getRealName(n, p);

        if (session != null) {
            session.setAttribute("name", n);
            session.setAttribute("password", p);
            session.setAttribute("realname", realname);
        }
        if (userDao.validateUser(n, p) == true) {
            String type = userTypeDao.getType(n, p);
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
        ArrayList cartitems =cartItemDao .getCartItems();
        session.setAttribute("cart", cartitems);
        Pagination ps = new Pagination();
        ps.UpdateCurrentPage(1);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}