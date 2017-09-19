package com.ymens.spring.manager;


import com.ymens.spring.beans.Cart;
import com.ymens.spring.beans.User;
import com.ymens.spring.dao.*;
import com.ymens.spring.interfaces.IAuthor;
import com.ymens.spring.interfaces.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
        orderDao = (OrderDao) ctx.getBean("orderDao");
        orderItemDao = (OrderItemDao) ctx.getBean("orderItemDao");
        ses = conf.getInitParameter("name");
        if (ses == null) {
            System.out.println("error");
        }
        ses = (String) service.getAttribute("name");
        if (ses == null){
            System.out.println("error");
        }
    }
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
            if(orderDao.setOrder(orderTotal, user_id) != 0) {
                order_id = orderDao.getId(orderTotal);
                for (i = 0; i < list.size(); i++) {
                    cart = (Cart) list.get(i);
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