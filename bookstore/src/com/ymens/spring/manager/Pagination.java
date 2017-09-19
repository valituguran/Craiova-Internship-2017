
package com.ymens.spring.manager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Pagination extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static int currentPage = 1;
    HttpSession session;
    public static int recordsPerPage = 9;
    private int noOfPages;
    String page;
    @Override
    public void init()
            throws ServletException {
    }
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String strAction = request.getParameter("action");
        String strcurrent = request.getParameter("currentpage");
        String strnoOfPage = request.getParameter("noOfPages");

        if(strcurrent == null) {
            currentPage = 1;
        }
        page = request.getParameter("page");
        try {
            noOfPages=Integer.parseInt(strnoOfPage);
            currentPage = Integer.parseInt(strcurrent);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (strAction != null && !strAction.equals("")) {
            if (strAction.equals("Prev")) {
                if(currentPage > 1) {
                    currentPage--;
                }
            }
            if (strAction.equals("Next")) {
                if(currentPage < noOfPages) {
                    currentPage++;
                }
            }
        }

    }
    public void UpdateCurrentPage(int value){
        this.currentPage= value;
    }
    public void UpdateTotalPage(int value){
        this.noOfPages= value;
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
        response.sendRedirect(page);
    }
}

