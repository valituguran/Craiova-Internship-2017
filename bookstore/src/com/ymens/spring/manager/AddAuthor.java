package com.ymens.spring.manager;
import com.ymens.spring.beans.Author;
import com.ymens.spring.beans.Book;
import com.ymens.spring.dao.AuthorsDao;
import com.ymens.spring.dao.BooksDao;
import com.ymens.spring.interfaces.IAuthor;
import com.ymens.spring.interfaces.IBook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddAuthor  extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        int id_author=0;
        int cnp=0;
        IAuthor authorDao = new AuthorsDao();
        Author author ;
        IBook bookDao = new BooksDao();
        String name = request.getParameter("name");
        String nationality=request.getParameter("nationality");
        String description_author=request.getParameter("description_author");
        String age=request.getParameter("age");
        int ag = 0;
        int isbn = 0;
        double price = 0.0;
        try{
            ag = Integer.parseInt(age);
            isbn = Integer.parseInt(AddBook.isbnString);
            price = Double.parseDouble(AddBook.priceString);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        author = new Author(name, ag, nationality, description_author, AddBook.CNP);
        authorDao.addAuthor(author);
        id_author = authorDao.getIdAuthor(cnp);
        Book book = new Book(AddBook.n, id_author, AddBook.isbn, AddBook.price, AddBook.description, AddBook.bytes);
        bookDao.addBook(book);
        RequestDispatcher rd=request.getRequestDispatcher("/selectbooksadminServlet");
        rd.forward(request,response);
        out.println("<font color=red>Please fill all the fields</font>");
        rd.include(request,response);
        out.close();
    }
}




