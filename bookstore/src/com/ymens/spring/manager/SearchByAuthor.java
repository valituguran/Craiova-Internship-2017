package com.ymens.spring.manager;


import com.ymens.hibernate.UserType;
import com.ymens.servlet.PaginationServlet;
import com.ymens.spring.beans.Book;
import com.ymens.spring.beans.User;
import com.ymens.spring.dao.AuthorsDao;
import com.ymens.spring.dao.BooksDao;
import com.ymens.spring.interfaces.IAuthor;
import com.ymens.spring.interfaces.IBook;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class SearchByAuthor extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static List<Integer> list = new LinkedList();
    public static List<Book> listBooks = new LinkedList();
    public static List<String> listAuthors = new LinkedList();
    HttpSession session;
    private static User user = new User();
    @Autowired
    private IBook book = new BooksDao();
    @Autowired
    private IAuthor author = new AuthorsDao();
    int author_id ;
    private String nameAuthor;

    @Override
    public void init()
            throws ServletException {
    }
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String n = request.getParameter("searchbyname");
        String name = request.getParameter("searchbyauthor");

        list = book.searchAuthors(name);
        listBooks = book.searchbyAuthor(list);
        Book b;
        int author_id;
        for(int i=0; i<listBooks.size(); i++) {
            b = (Book) listBooks.get(i);
            author_id = b.getAuthorId();
            nameAuthor = author.getName(author_id);
            listAuthors.add(nameAuthor);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String typelist = "searchbyauthor";
        process(request, response);
        session = request.getSession(false);
        session.setAttribute("searchbyauthor", listBooks);
        session.setAttribute("typelist", typelist);
        user.setUsername( (String)session.getAttribute("name"));
        user.setPassword((String) session.getAttribute("password"));
        PaginationServlet ps = new PaginationServlet();
        ps.UpdateCurrentPage(1);
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserType userType = new UserType();
        String usertype = userType.getType(user.getUsername(), user.getPassword());
        if (usertype.equalsIgnoreCase("user")) {
            getServletContext().getRequestDispatcher("/searchbyauthor_user.jsp").forward(request, response);
        } else if (usertype.equalsIgnoreCase("admin")){
            getServletContext().getRequestDispatcher("/searchbyauthor_admin.jsp").forward(request, response);
        } else{
            getServletContext().getRequestDispatcher("/searchbyauthor.jsp").forward(request, response);
        }
    }
}