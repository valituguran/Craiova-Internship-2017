package com.ymens.servlet;

import com.ymens.UserType;
import com.ymens.dao.LoginDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String n = request.getParameter("name");
		String p = request.getParameter("password");
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.setAttribute("name", n);
			session.setAttribute("password", p);

		}
		if (LoginDao.validate(n, p )) {
			String type = UserType.getType(n,p);
			if (type.equalsIgnoreCase("admin")) {
				getServletContext().getRequestDispatcher("/selectbooksadminServlet").forward(request, response);

			} else if (type.equalsIgnoreCase("user")) {
				//RequestDispatcher rd = request.getRequestDispatcher("selectbooksServlet");
				getServletContext().getRequestDispatcher("/selectbooksuserServlet").forward(request, response);
			}
		}else {
			out.print("<p style=\"color:red\">Sorry username or password error</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		}
		//out.close();
	}
}

