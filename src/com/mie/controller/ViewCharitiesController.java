package com.mie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mie.dao.CharityDao;
import com.mie.dao.EventDao;
import com.mie.model.Charity;
import com.mie.model.Event;


public class ViewCharitiesController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String CHARITIES = "/viewCharities.jsp";
	private static String EVENTS = "/viewCharityEvents.jsp";
	private static String CHARITIES_P = "/viewCharities_public.jsp";
	private static String EVENTS_P = "/viewCharityEvents_public.jsp";
	private static String CHARITIES_A = "/viewCharities_admin.jsp";
	private static String EVENTS_A = "/viewCharityEvents_admin.jsp";
	
	private CharityDao cdao;
	private EventDao edao;

	public ViewCharitiesController() {
		super();
		cdao = new CharityDao();
		edao = new EventDao();
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String forward = "";
		String action = request.getParameter("action");
		
		Object logged = session.getAttribute("username");
		Boolean admin = (Boolean)session.getAttribute("admin");
		
		if (action.equalsIgnoreCase("viewCharities")) {
			List<Charity> charities = new ArrayList<Charity>();
			charities = cdao.getAllCharities();
			request.setAttribute("charities", charities);

			if(logged == null) {
				forward = CHARITIES_P;
			}
			else if(admin){
				forward = CHARITIES_A;
			}
			else{forward = CHARITIES;	}

		
		} else if (action.equalsIgnoreCase("viewEvents")) {
			int id = Integer.parseInt(request.getParameter("charID"));
			String name = cdao.getCharityNameByCharityID(id);
			List<Event> events = new ArrayList<Event>();
			events = edao.getEventsByCharity(id);
			request.setAttribute("events", events);
			request.setAttribute("charity", name);
			if(logged == null) {
				forward = EVENTS_P;
			}
			else if(admin){
				forward = EVENTS_A;
			}
			else {

			forward = EVENTS;
			}
		
		} 
		RequestDispatcher view = request.getRequestDispatcher(forward);
		
		view.forward(request, response);
	}
}