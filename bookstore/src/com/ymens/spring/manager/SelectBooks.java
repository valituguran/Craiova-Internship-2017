package com.ymens.spring.manager;

import com.ymens.hibernate.User;
import com.ymens.hibernate.UserType;
import com.ymens.servlet.PaginationServlet;
import com.ymens.spring.beans.Book;
import com.ymens.spring.dao.AuthorsDao;
import com.ymens.spring.dao.BooksDao;
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
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

public class SelectBooks extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public List<Book> list = new LinkedList();
    public List<String> listAuthors = new LinkedList();
    HttpSession session;
    public static OutputStream o;
    String nameAuthor;
    private static User user = new User();
    @Autowired
    private IBook book = new BooksDao();
    @Autowired
    private IAuthor author = new AuthorsDao();
    private String ses;
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ServletContext context = getServletContext();
        WebApplicationContext ctx =
                WebApplicationContextUtils
                        .getWebApplicationContext(context);
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


        list = book.selectBooks();
        Book b;
         int author_id;
        for(int i=0; i<list.size(); i++) {
            b = (Book) list.get(i);
            author_id = b.getAuthorId();
            nameAuthor = author.getName(author_id);
            listAuthors.add(nameAuthor);
        }
        session = request.getSession(false);
        if (session != null) {
            session.setAttribute("list", list);
            session.setAttribute("listAuthors", listAuthors);
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
