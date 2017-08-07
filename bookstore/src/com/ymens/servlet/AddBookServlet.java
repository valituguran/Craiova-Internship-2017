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
    public static String cnp;
    public static String isbnString;
    public static String priceString;
    public static String description;
    public static long CNP = 0;
    public static int isbn = 0;
    public static double price = 0.0;
    public static int id_author;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        id_author = 0;
        Author author = new Author();
        n = request.getParameter("name");
        cnp = request.getParameter("cnp");
        isbnString = request.getParameter("isbn");
        priceString = request.getParameter("price");
        description = request.getParameter("description");
        HttpSession session = request.getSession(false);

        try{
            CNP = Long.parseLong(cnp);
            isbn = Integer.parseInt(isbnString);
            price = Double.parseDouble(priceString);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        if (AddAuthorDao.getIdAuthor(CNP) != 0){
            id_author = AddAuthorDao.getIdAuthor(CNP);
            author = PrintAuthor.getDetails(id_author);
            Book b = new Book(n, isbn, author, price, description);
            try {
                if (AddBookDao.addBook(b, CNP) == 1) {
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
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("/addauthor.jsp");
            rd.forward(request, response);
        }
    }
}
