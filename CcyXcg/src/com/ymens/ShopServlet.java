package com.ymens;

/**
 * Created by lucian.Nicolescu on 9/11/2017.
 */


import com.ymens.SellDao;
import dao.SearchDao;
import dao.SelectDao;
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

public class ShopServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session;
        session = request.getSession(false);
        String name = (String) session.getAttribute("name");
        String password =(String) session.getAttribute("password");
        UserDao object = new UserDao();
        int quantity;
        quantity = Integer.parseInt(request.getParameter("quantity"));
        int id= object.getID(name,password);
        session.setAttribute("id",id);
        String pairname=request.getParameter("curren");
        Double balance= Double.valueOf(request.getParameter("valoare"));
        session.setAttribute("valoare",balance);
        com.ymens.ShopDao object1 = new com.ymens.ShopDao();
        session.setAttribute("quantity",quantity);
        session.setAttribute("curren",pairname);
       /* if(SelectDao.select(pairname,id)==true){

        }*/
        if(com.ymens.ShopDao.shop(pairname,balance,id) == 1){
            response.sendRedirect("Currency.jsp");
            object.balance=object.balance-(quantity*balance);
            object1.modify(id,object.balance);
        }
        /*if(functiegasireperecheinsell==1){
            SellDao.updateSell(id,quantity+quantity,pairname);
        }*/

        out.close();
    }
}