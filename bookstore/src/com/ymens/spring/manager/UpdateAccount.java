package com.ymens.spring.manager;


import com.ymens.hibernate.UserType;
import com.ymens.servlet.PaginationServlet;
import com.ymens.spring.beans.User;
import com.ymens.spring.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static java.lang.System.out;

public class UpdateAccount extends HttpServlet {
    UserDao userDao = new UserDao();

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String strAction = request.getParameter("action");
        if (strAction != null && !strAction.equals("")) {
            try {
                if (strAction.equals("updatepassword")) {
                    updatePassword(request);
                }
                if (strAction.equals("Modifica")) {
                    updateRealName(request);
                }
                if (strAction.equals("Modifica adresa de email")) {
                    updateEmail(request);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        String n = (String) session.getAttribute("name");
        String p = (String) session.getAttribute("password");
        UserDao userDao = new UserDao();
        User user = userDao.getUser(n, p);
        PaginationServlet ps = new PaginationServlet();
        ps.UpdateCurrentPage(1);
        if (session != null) {
            session.setAttribute("currentuser", user);
        }

        UserType userType = new UserType();
        if (userType.getType(n, p).equalsIgnoreCase("user")) {
            getServletContext().getRequestDispatcher("/mycont_user.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/mycont_admin.jsp").forward(request, response);
        }
    }

    protected void updatePassword(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        String newPass = request.getParameter("password");
        String username = request.getParameter("username");
        if (userDao.UpdatePassword(newPass, username) == 1) {
            out.print("<p style=\"color:red\">Parola a fost modificata cu succes!</p>");
        }
    }

    protected void updateRealName(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        String newName = request.getParameter("realname");
        String username = request.getParameter("username");
        userDao.UpdateRealName(newName, username);
        session.setAttribute("realname", newName);
    }

    protected void updateEmail(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        String newEmail = request.getParameter("email");
        String username = request.getParameter("username");
        userDao.UpdateEmail(newEmail, username);
    }
}
