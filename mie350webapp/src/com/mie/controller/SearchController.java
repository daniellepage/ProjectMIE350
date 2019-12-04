package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.EventDao;
import com.mie.dao.UserDao;
import com.mie.model.Event;
import com.mie.model.User;

public class SearchController extends HttpServlet {
	/**
	 * This class only handles the SEARCH feature of the web app.
	 * 
	 * These are variables that lead to the appropriate JSP pages.
	 * 
	 * SEARCH_USER leads to the search results page.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String SEARCH_EVENTS = "/searchEventResult.jsp";
	
	private EventDao dao;

	public SearchController() {
		super();
		dao = new EventDao();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/**
		 * This method handles the retrieval of the search keyword entered by
		 * the user.
		 */

		String[] locs = request.getParameterValues("location");
		List<String> locations = Arrays.asList(locs);
		String[] don = request.getParameterValues("dontype");
		List<String> donationTypes = Arrays.asList(don);
		String[] cha = request.getParameterValues("chartype");
		List<String> charityTypes = Arrays.asList(cha);
		
		List<Event> searchResults = new ArrayList<Event>();
		searchResults = dao.getEventSearchResults(locations, donationTypes, charityTypes);


		RequestDispatcher view = request.getRequestDispatcher(SEARCH_EVENTS);
		request.setAttribute("events", searchResults);


		/**
		 * Redirect to the search results page after the list of Users
		 * matching the keywords has been retrieved.
		 */

		view.forward(request, response);
	}
}