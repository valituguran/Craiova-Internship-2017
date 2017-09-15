package com.ymens.servlet;

import com.ymens.dao.AddAuthorDao;
import com.ymens.dao.AddBookDao;
import com.ymens.dao.ManageBook;
import com.ymens.spring.beans.Author;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddAuthorServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        int id_author=0;
        int cnp=0;
        AddBookDao addbook = new AddBookDao();
        Author a=new Author();
        String name = request.getParameter("name");
        String nationality=request.getParameter("nationality");
        String description_author=request.getParameter("description_author");
        String age=request.getParameter("age");
        int ag = 0;
        int isbn = 0;
        double price = 0.0;
        try{
            ag = Integer.parseInt(age);
           isbn = Integer.parseInt(AddBookServlet.isbnString);
           price = Double.parseDouble(AddBookServlet.priceString);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        a = new Author(name, ag, nationality, description_author, AddBookServlet.CNP);
        AddAuthorDao.addAuthor(a);
        id_author = AddAuthorDao.getIdAuthor(cnp);
        ManageBook.addBook(AddBookServlet.n, isbn, id_author, price, AddBookServlet.description, AddBookServlet.bytes);
        //if(addbook.addBook(b, AddBookServlet.CNP) == 1){
        RequestDispatcher rd=request.getRequestDispatcher("/selectbooksadminServlet");
        rd.forward(request,response);
        // } else{
        // RequestDispatcher rd=request.getRequestDispatcher("addbook.jsp");
        out.println("<font color=red>Please fill all the fields</font>");
        rd.include(request,response);
        //  }
        out.close();
    }
}




