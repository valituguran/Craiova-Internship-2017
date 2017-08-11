package com.ymens.servlet;

import com.ymens.Book;
import com.ymens.CartItem;
import com.ymens.dao.CartDao;

import javax.servlet.RequestDispatcher;
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

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String strAction = request.getParameter("action");


        if(strAction!=null && !strAction.equals("")) {
            if(strAction.equals("add")) {
                addToCart(request);
            }  if (strAction.equals("Update")) {
                updateCart(request);
            } if (strAction.equals("Delete")) {
                deleteCart(request);
            }
        }

        String url = "/shoppingcart.jsp";
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(url);
        dispatcher.forward(request, response);
        getServletContext().getRequestDispatcher("/order.jsp").forward(request, response);
    }
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         processRequest(request, response);

    }
    public void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
            }
        }


    }

    protected void addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int cant = 0;
        double price = 0.0;
        String strTitle = request.getParameter("book");
        Book book = CartDao.getBook(strTitle);
        String strDescription = request.getParameter("description");
        String strPrice = request.getParameter("price");
        String strQuantity = request.getParameter("quantity");
        try{
            cant = Integer.parseInt(strQuantity);
            price = Double.parseDouble(strPrice);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(cant);
        cartItem.setUnitCost(price);
        cartItem.setTotalCost(cant*price);
        CartDao.alCartItems.add(cartItem);
        session.setAttribute("cart", CartDao.getCartItems());
    }

}

