package com.ymens.servlet;

import com.ymens.User;
import com.ymens.UserType;
import com.ymens.dao.MyContDao;
import com.ymens.dao.UpdateAccountDetailsDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static java.lang.System.out;

/**
 * Created by madalina.luca on 8/18/2017.
 */
public class UpdateAccountDetailsServlet extends HttpServlet {

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
        String n =(String) session.getAttribute("name");
        String p = (String)session.getAttribute("password");
        User user = MyContDao.select(n, p);
        if (session != null) {
            session.setAttribute("currentuser", user);
        }
        user.username = (String)session.getAttribute("name");
        user.password = (String)session.getAttribute("password");
        UserType userType = new UserType();
        if( userType.getType(user.username, user.password).equalsIgnoreCase("user")) {
            getServletContext().getRequestDispatcher("/mycont_user.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/mycont_admin.jsp").forward(request, response);
        }
    }

    protected void updatePassword(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        String newPass = request.getParameter("password");
        String username = request.getParameter("username");
        if(UpdateAccountDetailsDao.UpdatePassword(newPass, username)==1){
            out.print("<p style=\"color:red\">Parola a fost modificata cu succes!</p>");
        }
    }
    protected void updateRealName(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        String newName = request.getParameter("realname");
        String username = request.getParameter("username");
        UpdateAccountDetailsDao.UpdateRealName(newName, username);
    }
    protected void updateEmail(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        String newEmail = request.getParameter("email");
        String username = request.getParameter("username");
        UpdateAccountDetailsDao.UpdateEmail(newEmail, username);
    }
}
