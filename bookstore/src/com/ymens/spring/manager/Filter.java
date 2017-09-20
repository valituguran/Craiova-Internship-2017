package com.ymens.spring.manager;

import com.ymens.spring.beans.Book;
import com.ymens.spring.beans.PriceComparatorAsc;
import com.ymens.spring.beans.PriceComparatorDesc;
import com.ymens.spring.beans.User;
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
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class Filter extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public List<Book> list;
    HttpSession session;
    public static OutputStream o;
    protected int currentPage = 1;
    private static User user = new User();
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
        AutowireCapableBeanFactory ctx = ((ApplicationContext) getServletContext().getAttribute(
                "applicationContext")).getAutowireCapableBeanFactory();

        bookDao= (IBook) ctx.getBean("booksDao");
        authorDao = (IAuthor) ctx.getBean("authorsDao");
        userTypeDao = (UserTypeDao) ctx.getBean("userTypeDao");
        orderDao = (OrderDao) ctx.getBean("orderDao");
        orderItemDao = (OrderItemDao) ctx.getBean("orderItemDao");

    }
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String strfilterasc = request.getParameter("filterasc");
        String strfilterdesc = request.getParameter("filterdesc");
        String type_list = request.getParameter("typelist");
        session = request.getSession(false);

        switch (type_list) {
            case "filterbyprice":
                list = bookDao.selectBooks();
                break;
            case "filtersearchbyauthor":
                list = SearchByAuthor.listBooks;
                break;
            case "filtersearchbyname":
                list = SearchByName.list;
                break;
        }
        if (strfilterasc != null && !strfilterasc.equals("")) {
            Collections.sort(list, new PriceComparatorAsc());
        }
        if (strfilterdesc != null && !strfilterdesc.equals("")) {
            Collections.sort(list, new PriceComparatorDesc());
        }
        Pagination ps = new Pagination();
        ps.UpdateCurrentPage(1);
        ps.UpdateTotalPage(list.size() / 9);
        session.setAttribute(type_list, list);
        session.setAttribute("typelist", type_list);
        user.setUsername((String)session.getAttribute("name"));
        user.setPassword ((String)session.getAttribute("password"));
        String usertype = userTypeDao.getType(user.getUsername(), user.getPassword()) ;
        switch (type_list) {
            case "filterbyprice":
                if (usertype.equalsIgnoreCase("user")) {
                    getServletContext().getRequestDispatcher("/filterbyprice_user.jsp").forward(request, response);
                } else if (usertype.equalsIgnoreCase("admin")) {
                    getServletContext().getRequestDispatcher("/filterbyprice_admin.jsp").forward(request, response);
                } else {
                    getServletContext().getRequestDispatcher("/filterbyprice.jsp").forward(request, response);
                }
                break;
            case "filtersearchbyname":
                if (usertype.equalsIgnoreCase("user")) {
                    getServletContext().getRequestDispatcher("/searchbyname_user.jsp").forward(request, response);
                } else if (usertype.equalsIgnoreCase("admin")) {
                    getServletContext().getRequestDispatcher("/searchbyname_admin.jsp").forward(request, response);
                } else {
                    getServletContext().getRequestDispatcher("/searchbyname.jsp").forward(request, response);
                }
                break;
            case "filtersearchbyauthor":
                if (usertype.equalsIgnoreCase("user")) {
                    getServletContext().getRequestDispatcher("/searchbyauthor_user.jsp").forward(request, response);
                } else if (usertype.equalsIgnoreCase("admin")) {
                    getServletContext().getRequestDispatcher("/searchbyauthor_admin.jsp").forward(request, response);
                } else {
                    getServletContext().getRequestDispatcher("/searchbyauthor.jsp").forward(request, response);
                }
                break;
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        processRequest(request, response);
    }
}