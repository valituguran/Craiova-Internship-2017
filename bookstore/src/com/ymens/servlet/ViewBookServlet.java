package com.ymens.servlet;

import com.ymens.Book;
import com.ymens.User;
import com.ymens.dao.ViewBookDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Created by madalina.luca on 8/23/2017.
 */
public class ViewBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Book book = new Book();
    HttpSession session;
    private static User user = new User();

    @Override
    public void init()
            throws ServletException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = request.getParameter("title");
        String page = request.getParameter("pagetitle");
            book =  ViewBookDao.selectBook(title);
        session = request.getSession(false);
        if (session != null) {
            session.setAttribute("viewbook", book);
            session.setAttribute("page", page);
        }
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/detailsbook.jsp").forward(request, response);
    }
}
