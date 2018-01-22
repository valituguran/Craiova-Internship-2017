package com.ymens.servlet;

import com.ymens.hibernate.CartItem;
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
public static double orderTotal = 0.0;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        CartItem cartitem = new CartItem();
        HttpSession session = request.getSession();
        ArrayList<CartItem> list = CartDao.getCartItems();
        orderTotal = 0.0;
        int i;
        for( i=0; i<list.size();i++){
            orderTotal +=list.get(i).getTotalCost();
        }
        int order_id;
        String page = request.getParameter("returnpage");
        session.setAttribute("currentpage", page);
        String user =(String) session.getAttribute("name");
        int user_id = OrderDao.getUserId(user);
        if (orderTotal != 0) {
            if(OrderDao.setOrder(orderTotal, user_id) != 0) {
                order_id = OrderDao.getOrderId(orderTotal);
                for (i = 0; i < list.size(); i++) {
                    cartitem = (CartItem) list.get(i);
                    OrderDao.setOrderItem(order_id, cartitem.getBook().getName(), cartitem.getUnitCost());
                }
                session.setAttribute("order", list);
                RequestDispatcher rd = request.getRequestDispatcher("order.jsp");
                rd.forward(request, response);
            }
        }else {
            RequestDispatcher rd = request.getRequestDispatcher("shoppingcart_user.jsp");
            rd.forward(request, response);
        }
        CartDao cartDao = new CartDao();
        for(i=0; i<list.size();i++)
            cartDao.deleteCartItem(i);
        PaginationServlet ps = new PaginationServlet();
        ps.UpdateCurrentPage(1);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}