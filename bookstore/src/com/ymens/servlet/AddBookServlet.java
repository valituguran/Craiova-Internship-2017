package com.ymens.servlet;

import com.ymens.Author;
import com.ymens.Book;
import com.ymens.PrintAuthor;
import com.ymens.dao.AddAuthorDao;
import com.ymens.dao.AddBookDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by madalina.luca on 8/4/2017.
 */
public class AddBookServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public static String text;
    public static String n;
    public static String na;
    public static String isbn;
    public static String price;
    public static String description;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int id_author = 0;
        Author author = new Author();
        n = request.getParameter("name");
        na = request.getParameter("nameauthor");
        isbn = request.getParameter("isbn");
        price = request.getParameter("price");
        description = request.getParameter("description");
        HttpSession session = request.getSession(false);
        if (AddAuthorDao.getIdAuthor(na) != 0) {
            id_author = AddAuthorDao.getIdAuthor(na);
            author = PrintAuthor.getDetails(id_author);
            Book b = new Book(n, Integer.parseInt(isbn), author, Double.parseDouble(price), description);
            try {
                if (AddBookDao.addBook(b)) {
                    RequestDispatcher rd = request.getRequestDispatcher("/selectbooksadminServlet");
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("addbook.jsp");
                    out.println("<font color=red>Please fill all the fields</font>");
                    rd.include(request, response);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            out.close();
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/addauthor.jsp");
            rd.forward(request, response);
        }
    }
}
