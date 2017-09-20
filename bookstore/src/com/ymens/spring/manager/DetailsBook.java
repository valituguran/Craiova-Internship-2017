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
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
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
    protected AutowireCapableBeanFactory ctx;

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ctx = ((ApplicationContext) getServletContext().getAttribute(
                "applicationContext")).getAutowireCapableBeanFactory();

        bookDao= (IBook) ctx.getBean("booksDao");
        authorDao = (IAuthor) ctx.getBean("authorsDao");
        userTypeDao = (UserTypeDao) ctx.getBean("userTypeDao");
        orderDao = (OrderDao) ctx.getBean("orderDao");
        orderItemDao = (OrderItemDao) ctx.getBean("orderItemDao");


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
