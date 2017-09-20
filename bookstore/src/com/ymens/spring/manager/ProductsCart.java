package com.ymens.spring.manager;


import com.ymens.spring.beans.Book;
import com.ymens.spring.beans.Cart;
import com.ymens.spring.beans.User;
import com.ymens.spring.dao.*;
import com.ymens.spring.interfaces.IAuthor;
import com.ymens.spring.interfaces.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ProductsCart  extends HttpServlet {
    public static int nr = 0;
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
    CartItemDao cartItemDao;

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        AutowireCapableBeanFactory ctx;
        ctx = ((ApplicationContext) getServletContext().getAttribute(
                "applicationContext")).getAutowireCapableBeanFactory();
        bookDao= (IBook) ctx.getBean("booksDao");
        authorDao = (IAuthor) ctx.getBean("authorsDao");
        userDao = (UserDao) ctx.getBean("userDao");
        userTypeDao = (UserTypeDao) ctx.getBean("userTypeDao");
        cartItemDao = (CartItemDao) ctx.getBean("cartItemDao");
    }

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
        user = userDao.getUser(n, p);
        if (session != null) {
            session.setAttribute("currentuser", user);
        }
        user.setUsername((String)session.getAttribute("name"));
        user.setPassword((String)session.getAttribute("password"));
        if( userTypeDao.getType(user.getUsername(), user.getPassword()).equalsIgnoreCase("user")) {
            getServletContext().getRequestDispatcher("/shoppingcart_user.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/shoppingcart_admin.jsp").forward(request, response);
        }
        Pagination ps = new Pagination();
        ps.UpdateCurrentPage(1);
    }

    protected void deleteCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String title = request.getParameter("name");
        int id = CartItemDao.getItemBook(title);
        ArrayList<Cart> list =(ArrayList) session.getAttribute("cart");
        for(int i=0; i<list.size(); i++){
            if(CartItemDao.getItemBook(list.get(i).getBook().getName()) == id){
                cartItemDao.deleteCartItem(id);
                session.setAttribute("cart", cartItemDao.getCartItems());
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
        ArrayList<Cart> list =(ArrayList) session.getAttribute("cart");
        for(int i=0; i<list.size(); i++){
            if(CartItemDao.getItemBook(list.get(i).getBook().getName()) == id){
                cartItemDao.updateCartItem(id, strQuantity);
                session.setAttribute("cart", cartItemDao.getCartItems());
                nr = list.size();
            }
        }
        return status;
    }

    protected void addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String strTitle = request.getParameter("book");
        String description = request.getParameter("description");
        BooksDao bookDao = new BooksDao();
        Book book = bookDao.getBook(strTitle);
        String strPrice = request.getParameter("price");
        String strQuantity = request.getParameter("quantity");
        Cart cartItem = new Cart();
        cartItem.setBook(book);
        if(cartItemDao.getCartItems().size() > 0){
        for(int i=0 ;i<cartItemDao.getCartItems().size(); i++) {
            cartItem = (Cart) cartItemDao.getCartItems().get(i);
            if (strTitle.equalsIgnoreCase(cartItem.getBook().getName())) {
                cartItemDao.addExisting(i, strQuantity);
                break;
            }else {
                cartItemDao.addCartItem(book, description, strPrice, strQuantity);
                session.setAttribute("dbOrderTotal", CartItemDao.getOrderTotal());
                session.setAttribute("cart", cartItemDao.getCartItems());
                ArrayList<Cart> list = (ArrayList) session.getAttribute("cart");
                cartItemDao.setCartItems(list);
                nr = list.size();
                break;
            }
        }}else {
            cartItemDao.addCartItem(book, description, strPrice, strQuantity);
            session.setAttribute("dbOrderTotal", CartItemDao.getOrderTotal());
            session.setAttribute("cart", cartItemDao.getCartItems());
            ArrayList<Cart> list = (ArrayList) session.getAttribute("cart");
            cartItemDao.setCartItems(list);
            nr = list.size();
        }

    }
}

