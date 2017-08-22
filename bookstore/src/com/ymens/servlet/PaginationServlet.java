package com.ymens.servlet;

import com.ymens.User;
import com.ymens.dao.SelectBooksDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Created by madalina.luca on 8/22/2017.
 */
public class PaginationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static int currentPage = 1;
    public static LinkedList list = SelectBooksDao.select((currentPage - 1) * 9, 9);
    HttpSession session;
    public static int recordsPerPage = 9;
    public static int  noOfPages =1+ SelectBooksDao.select().size()/recordsPerPage;
    private static User user = new User();
    @Override
    public void init()
            throws ServletException {
    }
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String strAction = request.getParameter("action");
        String strcurrent = request.getParameter("currentpage");
        try {
            currentPage = Integer.parseInt(strcurrent);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (strAction != null && !strAction.equals("")) {
            if (strAction.equals("Prev")) {
                if(currentPage > 1) {
                    currentPage--;
                    list = SelectBooksDao.select((currentPage - 1) * 9, 9);
                }
            }
            if (strAction.equals("Next")) {
                if(currentPage < noOfPages+1) {
                    currentPage++;
                    list = SelectBooksDao.select((currentPage - 1) * 9, 9);
                }
            }
        }

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }

    }

