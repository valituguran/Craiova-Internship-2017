package com.ymens.spring.manager;


import com.ymens.dao.MyContDao;
import com.ymens.hibernate.User;
import com.ymens.hibernate.UserType;
import com.ymens.servlet.PaginationServlet;
import com.ymens.spring.beans.Book;
import com.ymens.spring.beans.Cart;
import com.ymens.spring.dao.BooksDao;
import com.ymens.spring.dao.CartItemDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ProductsCart  extends HttpServlet {
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
                if (updateCart(request) == false){
                    System.out.println("Error");
                } else {
                    updateCart(request);
                }
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
        if( userType.getType(user.getUsername(), user.getPassword()).equalsIgnoreCase("user")) {
            getServletContext().getRequestDispatcher("/shoppingcart_user.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/shoppingcart_admin.jsp").forward(request, response);
        }
        PaginationServlet ps = new PaginationServlet();
        ps.UpdateCurrentPage(1);
    }

    protected void deleteCart(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String title = request.getParameter("name");
        CartItemDao cartDao = new CartItemDao();
        int id = CartItemDao.getItemBook(title);
        ArrayList<Cart> list =(ArrayList) session.getAttribute("cart");
        for(int i=0; i<list.size(); i++){
            if(CartItemDao.getItemBook(list.get(i).getBook().getName()) == id){
                cartDao.deleteCartItem(id);
                session.setAttribute("cart", CartItemDao.getCartItems());
                nr = list.size();
            }
        }
    }

    protected boolean updateCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String strQuantity = request.getParameter("quantity");
        int quantity = 0;
        boolean status = true;
        try {
            quantity = Integer.parseInt(strQuantity);
        } catch(NumberFormatException e){
            status = false;
        }
        String title = request.getParameter("name");
        int id = CartItemDao.getItemBook(title);
        CartItemDao cartDao = new CartItemDao();
        ArrayList<Cart> list =(ArrayList) session.getAttribute("cart");
        for(int i=0; i<list.size(); i++){
            if(CartItemDao.getItemBook(list.get(i).getBook().getName()) == id){
                cartDao.updateCartItem(id, strQuantity);
                session.setAttribute("cart", CartItemDao.getCartItems());
                nr = list.size();
            }
        }
        return status;
    }

    protected void addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CartItemDao cartDao = new CartItemDao();
        String strTitle = request.getParameter("book");
        String description = request.getParameter("description");
        BooksDao bookDao = new BooksDao();
        Book book = bookDao.getBook(strTitle);
        String strPrice = request.getParameter("price");
        String strQuantity = request.getParameter("quantity");
        Cart cartItem = new Cart();
        cartItem.setBook(book);
        cartDao.addCartItem(book, description, strPrice, strQuantity);
        session.setAttribute("dbOrderTotal", CartItemDao.getOrderTotal());
        session.setAttribute("cart", CartItemDao.getCartItems());
        ArrayList<Cart> list =(ArrayList) session.getAttribute("cart");
        cartDao.setCartItems(list);
        nr = list.size();
    }
}

