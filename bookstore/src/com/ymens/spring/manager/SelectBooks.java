package com.ymens.spring.manager;

import com.ymens.spring.beans.Book;
import com.ymens.spring.beans.User;
import com.ymens.spring.dao.UserTypeDao;
import com.ymens.spring.interfaces.IAuthor;
import com.ymens.spring.interfaces.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

@Component
public class SelectBooks extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static List<Book> list = new LinkedList();
    public static List<String> listAuthors = new LinkedList();
    HttpSession session;
    public static OutputStream o;
    String nameAuthor;
    private static User user = new User();
    @Autowired
    private IBook book ;
    @Autowired
    private IAuthor author ;
    @Autowired
    private UserTypeDao userTypeDao ;
    private String ses;
    public SelectBooks() {}
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ServletContext context = getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(context);
        ServletContext service = conf.getServletContext();
        book= (IBook) ctx.getBean("booksDao");
        author = (IAuthor) ctx.getBean("authorsDao");
        userTypeDao = (UserTypeDao) ctx.getBean("userTypeDao");
        ses = conf.getInitParameter("name");
        if (ses == null) {
            System.out.println("error");
        }
        ses = (String) service.getAttribute("name");
        if (ses == null){
            System.out.println("error");
        }
    }

    public void process(){
        list = book.selectBooks();
        Book b;
        int author_id;
        for(int i=0; i<list.size(); i++) {
            b = list.get(i);
            author_id = b.getAuthorId();
            nameAuthor = author.getName(author_id);
            listAuthors.add(nameAuthor);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        process();
        session = request.getSession(false);
        if (session != null) {
            session.setAttribute("list", list);
            session.setAttribute("listAuthors", listAuthors);
            user.setUsername((String) session.getAttribute("name"));
            user.setPassword((String) session.getAttribute("password"));
        } else {
            session = request.getSession(true);
            session.setAttribute("list", list);
            session.setAttribute("listAuthors", listAuthors);
            user.setPassword("");
            user.setUsername("");
        }
        Pagination ps = new Pagination();
        ps.UpdateCurrentPage(1);
        if( user.getUsername().equalsIgnoreCase("") && user.getPassword().equalsIgnoreCase("")){
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else if( userTypeDao.getType(user.getUsername(), user.getPassword()).equalsIgnoreCase("user"))
        {
            getServletContext().getRequestDispatcher("/products_user.jsp").forward(request, response);
        }
        else  if( userTypeDao.getType(user.getUsername(), user.getPassword()).equalsIgnoreCase("admin"))
        {
            getServletContext().getRequestDispatcher("/products_admin.jsp").forward(request, response);
        }
        if(session == null && !user.getUsername().equalsIgnoreCase("") && !user.getPassword().equalsIgnoreCase("" )){
            response.sendRedirect("login.jsp");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
