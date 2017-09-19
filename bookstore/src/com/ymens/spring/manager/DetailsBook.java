package com.ymens.spring.manager;


import com.ymens.spring.beans.Author;
import com.ymens.spring.beans.Book;
import com.ymens.spring.dao.OrderDao;
import com.ymens.spring.dao.OrderItemDao;
import com.ymens.spring.dao.UserDao;
import com.ymens.spring.dao.UserTypeDao;
import com.ymens.spring.interfaces.IAuthor;
import com.ymens.spring.interfaces.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DetailsBook extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Book book = new Book();
    HttpSession session;
    @Autowired
    IBook bookDao ;
    @Autowired
    IAuthor authorDao ;
    @Autowired
    UserDao userDao ;
    @Autowired
    UserTypeDao userTypeDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderItemDao orderItemDao;
    private String ses;
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ServletContext context = getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(context);
        ServletContext service = conf.getServletContext();
        bookDao= (IBook) ctx.getBean("booksDao");
        authorDao = (IAuthor) ctx.getBean("authorsDao");
        userTypeDao = (UserTypeDao) ctx.getBean("userTypeDao");
        orderDao = (OrderDao) ctx.getBean("orderDao");
        orderItemDao = (OrderItemDao) ctx.getBean("orderItemDao");
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
        String title = request.getParameter("title");
        String page = request.getParameter("pagetitle");
        book =  bookDao.getBook(title);
        int id = bookDao.getIdAuthor(title);
        session = request.getSession(false);
        Pagination ps = new Pagination();
        ps.UpdateCurrentPage(1);
        Author a = authorDao.getAuthor(id);
        if (session != null) {
            session.setAttribute("viewbook", book);
            session.setAttribute("page", page);
            session.setAttribute("author", a);
        }
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/detailsbook.jsp").forward(request, response);
    }
}
