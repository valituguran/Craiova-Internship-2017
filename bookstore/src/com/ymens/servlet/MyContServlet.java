package com.ymens.servlet;

import com.ymens.hibernate.User;
import com.ymens.hibernate.UserType;
import com.ymens.dao.History;
import com.ymens.dao.MyContDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

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
        String n = (String) session.getAttribute("name");
        String p = (String) session.getAttribute("password");
        user = MyContDao.select(n, p);
        user.username = (String) session.getAttribute("name");
        user.password = (String) session.getAttribute("password");
        String type = request.getParameter("type");
        if (type.equals("accountdetails")){
            session.setAttribute("type", type);
            session.setAttribute("currentuser", user);
        } else if(type.equals("myorders")) {
            ArrayList list = History.getOrders(user.username);
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
            if (userType.getType(user.username, user.password).equalsIgnoreCase("user")) {
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


