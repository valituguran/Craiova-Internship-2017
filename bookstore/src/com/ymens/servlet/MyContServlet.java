package com.ymens.servlet;

import com.ymens.User;
import com.ymens.dao.MyContDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by madalina.luca on 8/3/2017.
 */
public class MyContServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    //public LinkedList list = new LinkedList();
    public User user;
    @Override
    public void init() throws ServletException {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        String n =(String) session.getAttribute("name");
        String p = (String)session.getAttribute("password");
        user = MyContDao.select(n, p);
        if (session != null) {
            session.setAttribute("currentuser", user);
        }
        //request.getRequestDispatcher("mycont.jsp").include(request, response);
        getServletContext().getRequestDispatcher("/mycont.jsp").forward(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);

    }





}


