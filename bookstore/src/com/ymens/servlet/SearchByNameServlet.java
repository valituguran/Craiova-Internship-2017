package com.ymens.servlet;

import com.ymens.User;
import com.ymens.UserType;
import com.ymens.dao.SearchByNameDao;

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
public class SearchByNameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static LinkedList list = new LinkedList();
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
        String n = request.getParameter("searchbyname");
        list = SearchByNameDao.searchByName(n);
        session = request.getSession(false);
        if (session != null) {
            session.setAttribute("searchbyname", list);
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
            getServletContext().getRequestDispatcher("/searchbyname_user.jsp").forward(request, response);
        }else  if (usertype.equalsIgnoreCase("admin")){
            getServletContext().getRequestDispatcher("/searchbyname_admin.jsp").forward(request, response);
        }
        else{
            getServletContext().getRequestDispatcher("/searchbyname.jsp").forward(request, response);
        }
    }
}