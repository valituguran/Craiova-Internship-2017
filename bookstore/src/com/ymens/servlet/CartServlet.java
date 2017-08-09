package com.ymens.servlet;

import com.ymens.Book;
import com.ymens.dao.CartDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
            } else if (strAction.equals("Update")) {
                updateCart(request);
            } else if (strAction.equals("Delete")) {
                deleteCart(request);
            }
        }
        String url = "/shoppingcart.jsp";
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(url);
        dispatcher.forward(request, response);

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
        String strItemIndex = request.getParameter("itemIndex");
        CartDao cartDao = null;
        int id = CartDao.getIdBook(strItemIndex);
        Object objCartDao = session.getAttribute("cart");
        if(objCartDao!=null) {
            cartDao = (CartDao) objCartDao ;
        } else {
            cartDao = new CartDao();
        }
        cartDao.deleteCartItem(id);
    }

    protected void updateCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String strQuantity = request.getParameter("quantity");
        String strItemIndex = request.getParameter("itemIndex");
        int id = CartDao.getIdBook(strItemIndex);

        CartDao cartDao = null;

        Object objCartDao = session.getAttribute("cart");
        if(objCartDao!=null) {
            cartDao = (CartDao) objCartDao ;
        } else {
            cartDao = new CartDao();
        }
        cartDao.updateCartItem(id, strQuantity);
    }

    protected void addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String strTitle = request.getParameter("book");
        Book book = CartDao.getBook(strTitle);
        String strDescription = request.getParameter("description");
        String strPrice = request.getParameter("price");
        String strQuantity = request.getParameter("quantity");

        CartDao cartDao = null;

        Object objCartDao = session.getAttribute("cart");

        if(objCartDao!=null) {
            cartDao = (CartDao) objCartDao ;
        } else {
            cartDao = new CartDao();
            session.setAttribute("cart", cartDao);
        }

        cartDao.addCartItem(book, strDescription, strPrice, strQuantity);
    }

}

