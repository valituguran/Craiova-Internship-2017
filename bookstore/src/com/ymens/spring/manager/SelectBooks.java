package com.ymens.spring.manager;

import com.ymens.spring.beans.Book;
import com.ymens.spring.beans.User;
import com.ymens.spring.dao.UserTypeDao;
import com.ymens.spring.interfaces.IAuthor;
import com.ymens.spring.interfaces.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class SelectBooks extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    private IBook book ;
    @Autowired
    private IAuthor author ;
    @Autowired
    private UserTypeDao userTypeDao ;
    public static List<Book> list = new LinkedList();
    public static List<String> listAuthors = new LinkedList();
    HttpSession session;
    String nameAuthor;
    private static User user = new User();
    public SelectBooks() {}
    protected AutowireCapableBeanFactory ctx;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ctx = ((ApplicationContext) getServletContext().getAttribute(
                "applicationContext")).getAutowireCapableBeanFactory();

        book= (IBook) ctx.getBean("booksDao");
        author = (IAuthor) ctx.getBean("authorsDao");
        userTypeDao = (UserTypeDao) ctx.getBean("userTypeDao");
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
