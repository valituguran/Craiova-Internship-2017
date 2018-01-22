package com.ymens.spring.manager;


import com.ymens.dao.OrderDao;
import com.ymens.servlet.PaginationServlet;
import com.ymens.spring.beans.Cart;
import com.ymens.spring.dao.CartItemDao;
import com.ymens.spring.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Order extends HttpServlet {
    public static double orderTotal = 0.0;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cart cart = new Cart();
        HttpSession session = request.getSession();
        ArrayList<Cart> list = CartItemDao.getCartItems();
        orderTotal = 0.0;
        int i;
        for( i=0; i<list.size();i++){
            cart = list.get(i);
            orderTotal +=cart.getUnitCost();
        }
        int order_id;
        String page = request.getParameter("returnpage");
        session.setAttribute("currentpage", page);
        String user =(String) session.getAttribute("name");
        String pass = (String) session.getAttribute("password");
        UserDao userDao = new UserDao();
        int user_id = userDao.getId(user, pass);
        CartItemDao cartItemDao = new CartItemDao();
        if (orderTotal != 0) {
            if(OrderDao.setOrder(orderTotal, user_id) != 0) {
                order_id = OrderDao.getOrderId(orderTotal);
                for (i = 0; i < list.size(); i++) {
                    cart = (Cart) list.get(i);
                    OrderDao.setOrderItem(order_id, cart.getBook().getName(), cart.getUnitCost());
                }
                session.setAttribute("order", list);
                RequestDispatcher rd = request.getRequestDispatcher("order.jsp");
                rd.forward(request, response);
                ArrayList<Cart> listRemove =(ArrayList) session.getAttribute("cart");
                listRemove.clear();
                session.setAttribute("cart", null);
            }
        }else {
            RequestDispatcher rd = request.getRequestDispatcher("shoppingcart_user.jsp");
            rd.forward(request, response);
        }

        PaginationServlet ps = new PaginationServlet();
        ps.UpdateCurrentPage(1);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}