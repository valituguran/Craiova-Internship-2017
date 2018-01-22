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
    private static User user = new User();
    @Autowired
    private IBook book = new BooksDao();
    @Autowired
    private IAuthor author = new AuthorsDao();
    private String nameAuthor;
    public static List<String> listAuthors = new LinkedList();
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String n = request.getParameter("searchbyname");
        list = book.searchByName(n);
        Book b;
        int author_id;
        for(int i=0; i<list.size(); i++) {
            b = (Book) list.get(i);
            author_id = b.getAuthorId();
            nameAuthor = author.getName(author_id);
            listAuthors.add(nameAuthor);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String typelist = "searchbyname";
        process(request, response);
        String name = author.getName(1);
        session = request.getSession(false);
        if (session != null) {
            session.setAttribute("searchbyname", list);
            session.setAttribute("typelist", typelist);
        }
        user.username = (String) session.getAttribute("name");
        user.password = (String) session.getAttribute("password");
        PaginationServlet ps = new PaginationServlet();
        ps.UpdateCurrentPage(1);
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserType userType = new UserType();
        String usertype = userType.getType(user.username, user.password);
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