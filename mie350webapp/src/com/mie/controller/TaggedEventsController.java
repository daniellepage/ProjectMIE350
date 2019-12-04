package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.EventDao;
import com.mie.dao.TaggedEventsDao;
import com.mie.dao.UserDao;
import com.mie.model.Event;
import com.mie.model.User;

public class TaggedEventsController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private EventDao edao;
	private TaggedEventsDao tdao;
	private UserDao udao;
	
	public TaggedEventsController() {
		super();
		edao = new EventDao();
		tdao = new TaggedEventsDao();
		udao = new UserDao();
	}

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		String forward = "";
		String action = request.getParameter("action");
		String username = (String)request.getSession().getAttribute("username");

		if(action.equalsIgnoreCase("listInterested")) {
			HashMap<Integer, String> eventTags = tdao.getEventTags(username);

			ArrayList<Event> interestedEvents = edao.getEventsFromIDList(tdao.getInterestedEventIDs(eventTags));
			request.setAttribute("intevents", interestedEvents);
			forward = "/interestedEvents.jsp";
		}
		else if(action.equalsIgnoreCase("listConfirmed")) {
			HashMap<Integer, String> eventTags = tdao.getEventTags(username);

			ArrayList<Event> confirmedEvents = edao.getEventsFromIDList(tdao.getConfirmedEventIDs(eventTags));
			request.setAttribute("confevents", confirmedEvents);
			forward = "/confirmedEvents.jsp";
		}
		else if(action.equalsIgnoreCase("setConfirm")){
			int eventID = Integer.parseInt(request.getParameter("eventID"));
			tdao.setEventConfirmed(eventID, username);
			forward = "/setConfirmedEvent.jsp";	
		}
		else if(action.equalsIgnoreCase("setInterested")){
			int eventID = Integer.parseInt(request.getParameter("eventID"));
			tdao.setEventInterested(eventID, username);
			forward = "/setInterestedEvent.jsp";
		}
		else if(action.equalsIgnoreCase("deleteEvent")){
			int eventID = Integer.parseInt(request.getParameter("eventID"));
			tdao.deleteEvent(eventID, username);
			forward = "/deletedEvent.jsp";
		}
		
				
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
	}
	
}
