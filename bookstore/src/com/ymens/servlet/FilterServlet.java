package com.ymens.servlet;

import com.ymens.hibernate.PriceComparatorAsc;
import com.ymens.hibernate.PriceComparatorDesc;
import com.ymens.hibernate.User;
import com.ymens.hibernate.UserType;
import com.ymens.dao.SelectBooksDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by madalina.luca on 8/25/2017.
 */
public class FilterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LinkedList list = new LinkedList();
    HttpSession session;
    public static OutputStream o;
    protected int currentPage = 1;
    private static User user = new User();

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String strfilterasc = request.getParameter("filterasc");
        String strfilterdesc = request.getParameter("filterdesc");
        String type_list = request.getParameter("typelist");
        session = request.getSession(false);
        PaginationServlet ps = new PaginationServlet();
        ps.UpdateCurrentPage(1);
        ps.UpdateTotalPage(list.size() / 9);
        switch (type_list) {
            case "filterbyprice":
                list = SelectBooksDao.select();
                break;
            case "filtersearchbyauthor":
                list = SearchByAuthorServlet.list;
                break;
            case "filtersearchbyname":
                list = SearchByNameServlet.list;
                break;
        }
        if (strfilterasc != null && !strfilterasc.equals("")) {
            Collections.sort(list, new PriceComparatorAsc());
        }
        if (strfilterdesc != null && !strfilterdesc.equals("")) {
            Collections.sort(list, new PriceComparatorDesc());
        }
        session.setAttribute(type_list, list);
        session.setAttribute("typelist", type_list);
        user.username = (String) session.getAttribute("name");
        user.password = (String) session.getAttribute("password");
        UserType userType = new UserType();
        String usertype = userType.getType(user.username, user.password);
        switch (type_list) {
            case "filterbyprice":
                if (usertype.equalsIgnoreCase("user")) {
                    getServletContext().getRequestDispatcher("/filterbyprice_user.jsp").forward(request, response);
                } else if (usertype.equalsIgnoreCase("admin")) {
                    getServletContext().getRequestDispatcher("/filterbyprice_admin.jsp").forward(request, response);
                } else {
                    getServletContext().getRequestDispatcher("/filterbyprice.jsp").forward(request, response);
                }
                break;
            case "filtersearchbyname":
                if (usertype.equalsIgnoreCase("user")) {
                    getServletContext().getRequestDispatcher("/searchbyname_user.jsp").forward(request, response);
                } else if (usertype.equalsIgnoreCase("admin")) {
                    getServletContext().getRequestDispatcher("/searchbyname_admin.jsp").forward(request, response);
                } else {
                    getServletContext().getRequestDispatcher("/searchbyname.jsp").forward(request, response);
                }
                break;
            case "filtersearchbyauthor":
                if (usertype.equalsIgnoreCase("user")) {
                    getServletContext().getRequestDispatcher("/searchbyauthor_user.jsp").forward(request, response);
                } else if (usertype.equalsIgnoreCase("admin")) {
                    getServletContext().getRequestDispatcher("/searchbyauthor_admin.jsp").forward(request, response);
                } else {
                    getServletContext().getRequestDispatcher("/searchbyauthor.jsp").forward(request, response);
                }
                break;
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        processRequest(request, response);
    }
}

