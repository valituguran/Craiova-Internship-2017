package com.ymens.servlet;

import com.ymens.hibernate.UserType;
import com.ymens.dao.CartDao;
import com.ymens.dao.LoginDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String n = request.getParameter("name");
		String p = request.getParameter("password");
		HttpSession session = request.getSession(false);
		String realname = LoginDao.getRealName(n, p);
		if (session != null) {
			session.setAttribute("name", n);
			session.setAttribute("password", p);
			session.setAttribute("realname", realname);
		}
		if (LoginDao.validate(n, p )) {
			String type = UserType.getType(n,p);
			if (type.equalsIgnoreCase("admin")) {
				session.setAttribute("null", "no");
				session.setAttribute("type", "admin");
				getServletContext().getRequestDispatcher("/selectbooksadminServlet").forward(request, response);
//				getServletContext().getRequestDispatcher("/paginationServlet").forward(request, response);
			}else if (type.equalsIgnoreCase("user")) {
				session.setAttribute("null", "no");
				session.setAttribute("type", "user");
				getServletContext().getRequestDispatcher("/selectbooksuserServlet").forward(request, response);
			}
		} else {
			out.print("<p style=\"color:red\">Sorry username or password error</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		}
		ArrayList cartitems = CartDao.getCartItems();
		session.setAttribute("cart", cartitems);
		PaginationServlet ps = new PaginationServlet();
		ps.UpdateCurrentPage(1);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}