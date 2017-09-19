package com.ymens.spring.manager;


import com.ymens.spring.beans.User;
import com.ymens.spring.dao.UserDao;
import com.ymens.spring.dao.UserTypeDao;
import com.ymens.spring.interfaces.IAuthor;
import com.ymens.spring.interfaces.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static java.lang.System.out;

public class UpdateAccount extends HttpServlet {
    @Autowired
    IBook bookDao ;
    @Autowired
    IAuthor authorDao ;
    @Autowired
    UserDao userDao ;
    @Autowired
    UserTypeDao userTypeDao;
    private String ses;
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ServletContext context = getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(context);
        ServletContext service = conf.getServletContext();
        bookDao= (IBook) ctx.getBean("booksDao");
        authorDao = (IAuthor) ctx.getBean("authorsDao");
        userTypeDao = (UserTypeDao) ctx.getBean("userTypeDao");
        ses = conf.getInitParameter("name");
        if (ses == null) {
            System.out.println("error");
        }
        ses = (String) service.getAttribute("name");
        if (ses == null){
            System.out.println("error");
        }
    }
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
        Pagination ps = new Pagination();
        ps.UpdateCurrentPage(1);
        if (session != null) {
            session.setAttribute("currentuser", user);
        }
        if (userTypeDao.getType(n, p).equalsIgnoreCase("user")) {
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
