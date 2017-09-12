package com.ymens.spring.manager;

import com.ymens.hibernate.User;
import com.ymens.hibernate.UserType;
import com.ymens.servlet.PaginationServlet;
import com.ymens.spring.beans.Book;
import com.ymens.spring.dao.BooksDao;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

public class SelectBooks extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public List<Book> list = new LinkedList();
    HttpSession session;
    public static OutputStream o;
    private static User user = new User();
    DataSource ds;
    private String ses;
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ServletContext context = getServletContext();
        WebApplicationContext ctx =
                WebApplicationContextUtils
                        .getWebApplicationContext(context);
        ds = (DataSource) ctx.getBean("dataSource");
        ServletContext service = conf.getServletContext();
        ses = conf.getInitParameter("name");
        if (ses == null) {
            System.out.println("error");
        }
        ses = (String) service.getAttribute("name");
        if (ses == null){
            System.out.println("error");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        list = BooksDao.selectBooks(ds);
        session = request.getSession(false);
        if (session != null) {
            session.setAttribute("list", list);
        }
        user.username = (String)session.getAttribute("name");
        user.password = (String)session.getAttribute("password");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        UserType userType = new UserType();
        PaginationServlet ps = new PaginationServlet();
        ps.UpdateCurrentPage(1);
        if(session == null){
            response.sendRedirect("login.jsp");
        }
        if( userType.getType(user.username, user.password).equalsIgnoreCase("user")) {
            getServletContext().getRequestDispatcher("/products_user.jsp").forward(request, response);
        }
        else
        {
            getServletContext().getRequestDispatcher("/products_admin.jsp").forward(request, response);
        }

    }
}
