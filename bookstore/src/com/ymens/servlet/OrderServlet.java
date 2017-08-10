package com.ymens.servlet;

import com.ymens.CartItem;
import com.ymens.dao.CartDao;
import com.ymens.dao.OrderDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by madalina.luca on 8/9/2017.
 */
public class OrderServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String strOrderTotal = request.getParameter("orderTotal");
        CartItem cartitem = new CartItem();
        double orderTotal = 0.0;
        HttpSession session = request.getSession();
        ArrayList list = CartDao.getCartItems();
        int i = 0;
        int order_id;
        try {
            orderTotal = Double.parseDouble(strOrderTotal);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (orderTotal != 0) {
            OrderDao.setOrder(orderTotal);
            order_id = OrderDao.getOrderId(orderTotal);
            for (i = 0; i < list.size(); i++) {
                cartitem = (CartItem) list.get(i);
                OrderDao.setOrderItem(order_id, cartitem.getBook().getNume(), cartitem.getTotalCost());
                RequestDispatcher rd = request.getRequestDispatcher("products_admin.jsp");
                rd.forward(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("shoppingcart.jsp");
            rd.forward(request, response);
        }
            getServletContext().getRequestDispatcher("/shoppingcart.jsp").forward(request, response);
        }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         doPost(request, response);
    }
}