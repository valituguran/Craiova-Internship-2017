package com.ymens;

/**
 * Created by lucian.Nicolescu on 9/11/2017.
 */


import dao.BuyDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SellServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session;
        session = request.getSession(false);
        UserDao user = new UserDao();
        int quantity= Integer.parseInt(request.getParameter("quant"));
        String name = (String) session.getAttribute("name");
        String password =(String) session.getAttribute("password");
        String pairname=request.getParameter("pairtosell");
        Double balance= Double.valueOf(request.getParameter("valuetosell"));
        UserDao object = new UserDao();
        int id= object.getID(name,password);
        com.ymens.ShopDao object1 = new com.ymens.ShopDao();
        if(quantity>0) {
            user.balance = user.balance + (quantity * balance);
            object1.modify(id, user.balance);
            com.ymens.SellDao.updateSell(id, BuyDao.selectquantity(pairname, id) - quantity, pairname);
        }
        else if(quantity==0){
            com.ymens.SellDao.sell(pairname,balance);
        }
        response.sendRedirect("myAccount.jsp");
        out.close();
    }
}