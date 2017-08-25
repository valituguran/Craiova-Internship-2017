package com.ymens.servlet;

import com.ymens.Book;
import com.ymens.CartItem;
import com.ymens.User;
import com.ymens.UserType;
import com.ymens.dao.CartDao;
import com.ymens.dao.MyContDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by madalina.luca on 8/9/2017.
 */
public class CartServlet extends HttpServlet {
    //public static final String addToCart
    public User user;
    public static int nr = 0;
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String strAction = request.getParameter("action");
        if(strAction!=null && !strAction.equals("")) {
            if(strAction.equals("add")) {
                addToCart(request);
            }  if (strAction.equals("modifica")) {
                updateCart(request);
            } if (strAction.equals("sterge")) {
                deleteCart(request);
            }
        }
    }
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    public void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        String n =(String) session.getAttribute("name");
        String p = (String)session.getAttribute("password");
        user = MyContDao.select(n, p);
        if (session != null) {
            session.setAttribute("currentuser", user);
        }
        user.username = (String)session.getAttribute("name");
        user.password = (String)session.getAttribute("password");
        UserType userType = new UserType();
        if( userType.getType(user.username, user.password).equalsIgnoreCase("user")) {
            getServletContext().getRequestDispatcher("/shoppingcart_user.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/shoppingcart_admin.jsp").forward(request, response);
        }
    }

    protected void deleteCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String title = request.getParameter("name");
        CartDao cartDao = new CartDao();
        int id = CartDao.getIdBook(title);
        ArrayList<CartItem> list =(ArrayList) session.getAttribute("cart");
        for(int i=0; i<list.size(); i++){
            if(CartDao.getIdBook(list.get(i).getBook().getNume()) == id){
                cartDao.deleteCartItem(id);
                session.setAttribute("cart", CartDao.getCartItems());
                nr = list.size();
            }
        }
    }

    protected void updateCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String strQuantity = request.getParameter("quantity");
        String title = request.getParameter("name");
        int id = CartDao.getIdBook(title);
        CartDao cartDao = new CartDao();
        ArrayList<CartItem> list =(ArrayList) session.getAttribute("cart");
        for(int i=0; i<list.size(); i++){
            if(CartDao.getIdBook(list.get(i).getBook().getNume()) == id){
                cartDao.updateCartItem(id, strQuantity);
                session.setAttribute("cart", CartDao.getCartItems());
                nr = list.size();
            }
        }
    }

    protected void addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CartDao cartDao = new CartDao();
        String strTitle = request.getParameter("book");
        String description = request.getParameter("description");
        Book book = CartDao.getBook(strTitle);
        String strPrice = request.getParameter("price");
        String strQuantity = request.getParameter("quantity");
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartDao.addCartItem(book, description, strPrice, strQuantity);
        session.setAttribute("dbOrderTotal", CartDao.getOrderTotal());
        session.setAttribute("cart", CartDao.getCartItems());
        ArrayList<CartItem> list =(ArrayList) session.getAttribute("cart");
        nr = list.size();
    }
}

