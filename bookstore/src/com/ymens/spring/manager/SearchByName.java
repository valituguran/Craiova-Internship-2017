package com.ymens.spring.manager;

import com.ymens.spring.beans.Book;
import com.ymens.spring.beans.User;
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
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class SearchByName extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static List<Book> list = new LinkedList();
    HttpSession session;
    private String nameAuthor;
    public static List<String> listAuthors = new LinkedList();
    private static User user = new User();
    @Autowired
    IBook bookDao ;
    @Autowired
    IAuthor authorDao ;
    @Autowired
    UserDao userDao ;
    @Autowired
    UserTypeDao userTypeDao;

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        AutowireCapableBeanFactory ctx;
        ctx = ((ApplicationContext) getServletContext().getAttribute(
                "applicationContext")).getAutowireCapableBeanFactory();
        bookDao= (IBook) ctx.getBean("booksDao");
        authorDao = (IAuthor) ctx.getBean("authorsDao");
        userTypeDao = (UserTypeDao) ctx.getBean("userTypeDao");
    }

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String n = request.getParameter("searchbyname");
        list = bookDao.searchByName(n);
        Book b;
        int author_id;
        for(int i=0; i<list.size(); i++) {
            b =  list.get(i);
            author_id = b.getAuthorId();
            nameAuthor = authorDao.getName(author_id);
            listAuthors.add(nameAuthor);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String typelist = "searchbyname";
        process(request, response);
        String name = authorDao.getName(1);
        session = request.getSession(false);
        if (session != null) {
            session.setAttribute("searchbyname", list);
            session.setAttribute("typelist", typelist);
        }
        user.setUsername((String) session.getAttribute("name"));
        user.setPassword((String) session.getAttribute("password"));
        Pagination ps = new Pagination();
        ps.UpdateCurrentPage(1);
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String usertype = userTypeDao.getType(user.getUsername(), user.getPassword());
        if (usertype.equalsIgnoreCase("user")) {
            getServletContext().getRequestDispatcher("/searchbyname_user.jsp").forward(request, response);
        }else  if (usertype.equalsIgnoreCase("admin")){
            getServletContext().getRequestDispatcher("/searchbyname_admin.jsp").forward(request, response);
        }
        else{
            getServletContext().getRequestDispatcher("/searchbyname.jsp").forward(request, response);
        }
    }
}