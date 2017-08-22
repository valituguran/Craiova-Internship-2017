package com.ymens.servlet;

import com.ymens.User;
import com.ymens.UserType;
import com.ymens.dao.SelectBooksDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

/**
 * Created by madalina.luca on 8/1/2017.
 */
public class SelectBooksServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public LinkedList list = new LinkedList();
    HttpSession session;
    public static OutputStream o;
    private static User user = new User();
    @Override
    public void init()
            throws ServletException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        list = SelectBooksDao.select();
        session = request.getSession(false);
        if (session != null) {
            session.setAttribute("list", list);
        }
        user.username = (String)session.getAttribute("name");
        user.password = (String)session.getAttribute("password");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        UserType userType = new UserType();
        if( userType.getType(user.username, user.password).equalsIgnoreCase("user")) {
            getServletContext().getRequestDispatcher("/products_user.jsp").forward(request, response);
            getServletContext().getRequestDispatcher("/mycont_user.jsp").forward(request, response);
            getServletContext().getRequestDispatcher("/searchbyauthor_user.jsp").forward(request, response);
            getServletContext().getRequestDispatcher("/searchauthorServlet").forward(request, response);
        }
        else
        {
            getServletContext().getRequestDispatcher("/products_admin.jsp").forward(request, response);
            getServletContext().getRequestDispatcher("/mycont_admin.jsp").forward(request, response);
            getServletContext().getRequestDispatcher("/searchbyname_admin.jsp").forward(request, response);
            getServletContext().getRequestDispatcher("/searchbyauthor_admin.jsp").forward(request, response);
        }
    }
}