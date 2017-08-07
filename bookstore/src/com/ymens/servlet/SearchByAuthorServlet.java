package com.ymens.servlet;

import com.ymens.User;
import com.ymens.UserType;
import com.ymens.dao.SearchByAuthorDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Created by madalina.luca on 8/1/2017.
 */
public class SearchByAuthorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LinkedList list = new LinkedList();
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
        String name = request.getParameter("searchbyauthor");
        list = SearchByAuthorDao.searchByAuthor(name);
        session = request.getSession(false);
        if (session != null) {
            session.setAttribute("searchbyauthor", list);
        }
        user.username = (String) session.getAttribute("name");
        user.password = (String) session.getAttribute("password");
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserType userType = new UserType();
        String usertype = userType.getType(user.username, user.password);
        if (usertype.equalsIgnoreCase("user")) {
            getServletContext().getRequestDispatcher("/searchbyauthor_user.jsp").forward(request, response);

        } else {
            getServletContext().getRequestDispatcher("/searchbyauthor_admin.jsp").forward(request, response);
        }
    }
}