package com.ymens.servlet;

import com.ymens.dao.LoginDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
		if (session != null)
			session.setAttribute("name", n);

		if (LoginDao.validate(n, p) == true) {
               if(LoginDao.getUserType(n, p)==1) {

				   RequestDispatcher rd = request.getRequestDispatcher("welcome_admin.jsp");
				   rd.include(request, response);
			   }
			   else if(LoginDao.getUserType(n, p) == 2){
				   RequestDispatcher rd = request.getRequestDispatcher("welcome_user.jsp");
				   rd.include(request, response);
			   }
			} else {
				out.print("<p style=\"color:red\">Sorry username or password error</p>");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.include(request, response);
			}

			out.close();
		}
	}
