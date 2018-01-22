package com.ymens.spring.manager;

import com.ymens.dao.History;
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
import java.util.ArrayList;

public class AccountDetails extends HttpServlet {

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
        String n = (String) session.getAttribute("name");
        String p = (String) session.getAttribute("password");
        UserDao userDao = new UserDao();
        user = userDao.getUser(n, p);
        user.setUsername(n);
        user.setPassword(p);
        String type = request.getParameter("type");
        if (type.equals("accountdetails")){
            session.setAttribute("type", type);
            session.setAttribute("currentuser", user);
        } else if(type.equals("myorders")) {
            ArrayList list = History.getOrders(user.getUsername());
            session.setAttribute("type", type);
            session.setAttribute("orders", list);
            String string, str;
            int book_id;
            double price;
            PaginationServlet ps = new PaginationServlet();
            ps.UpdateCurrentPage(1);
            for (int i = 0; i < list.size(); i++) {
                string = list.get(i).toString();
                price = History.getTotalPrice(string);
                str = String.format("%s%d", "orders", i);
                ArrayList list1 = History.getOrderItems(string);
                session.setAttribute(str, list1);
                session.setAttribute("price" + str, price);
            }
        }
        UserType userType = new UserType();
        if (userType.getType(user.getUsername(), user.getPassword()).equalsIgnoreCase("user")) {
            getServletContext().getRequestDispatcher("/mycont_user.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/mycont_admin.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);


    }





}
