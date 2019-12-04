package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.UserDao;
import com.mie.model.User;

public class UserController extends HttpServlet {
	/**
	 * This class handles all insert/edit/list functions of the servlet.
	 * 
	 * These are variables that lead to the appropriate JSP pages. INSERT leads
	 * to the Add A User page. EDIT leads to the Edit A User page.
	 * LIST_User_PUBLIC leads to the public listing of Users.
	 * LIST_User_ADMIN leads to the admin-only listing of Users (for them
	 * to modify User information).
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private UserDao dao;

	public UserController() {
		super();
		dao = new UserDao();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String forward = "";
		String action = request.getParameter("action");
		String username = (String)request.getSession().getAttribute("username");

		if (action.equalsIgnoreCase("deactivate")) {
			dao.deactivateUser(username); 

			forward = "/deactivated.jsp";	
		
		} else if (action.equalsIgnoreCase("reactivate")) {
			dao.reactivateUser(username);
			forward = "/reactivated.jsp";
		
		} 

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

}