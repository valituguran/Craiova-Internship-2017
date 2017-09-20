package com.ymens.spring.manager;


import com.ymens.spring.beans.Cart;
import com.ymens.spring.beans.User;
import com.ymens.spring.dao.*;
import com.ymens.spring.interfaces.IAuthor;
import com.ymens.spring.interfaces.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class Order extends HttpServlet {
    public static double orderTotal = 0.0;
    public User user;
    @Autowired
    IBook bookDao ;
    @Autowired
    IAuthor authorDao ;
    @Autowired
    UserDao userDao ;
    @Autowired
    UserTypeDao userTypeDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    private CartItemDao cartItemDao;

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        AutowireCapableBeanFactory ctx;
        ctx = ((ApplicationContext) getServletContext().getAttribute(
                "applicationContext")).getAutowireCapableBeanFactory();
        bookDao= (IBook) ctx.getBean("booksDao");
        authorDao = (IAuthor) ctx.getBean("authorsDao");
        userDao = (UserDao) ctx.getBean("userDao");
        userTypeDao = (UserTypeDao) ctx.getBean("userTypeDao");
        orderDao = (OrderDao) ctx.getBean("orderDao");
        orderItemDao = (OrderItemDao) ctx.getBean("orderItemDao");
        cartItemDao = (CartItemDao) ctx.getBean("cartItemDao");

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        Cart cart ;
        HttpSession session = request.getSession();
        ArrayList<Cart> list = cartItemDao.getCartItems();
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
        int user_id = userDao.getId(user, pass);
        if (orderTotal != 0) {
            if(orderDao.setOrder(orderTotal, user_id) != 0) {
                order_id = orderDao.getId(orderTotal);
                for (i = 0; i < list.size(); i++) {
                    cart = list.get(i);
                    orderItemDao.setOrderItem(order_id, cart.getBook().getName(), cart.getUnitCost());
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
        Pagination ps = new Pagination();
        ps.UpdateCurrentPage(1);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}