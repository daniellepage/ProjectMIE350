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
import javax.servlet.http.HttpSession;

import com.mie.dao.EventDao;
import com.mie.dao.TaggedEventsDao;
import com.mie.dao.UserDao;
import com.mie.model.Event;
import com.mie.model.User;


public class TaggedEventsController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private EventDao edao;
	private TaggedEventsDao tdao;
	
	public TaggedEventsController() {
		super();
		edao = new EventDao();
		tdao = new TaggedEventsDao();
	}

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		HttpSession session = request.getSession();
		String forward = "";
		String action = request.getParameter("action");
		String username = (String)session.getAttribute("username");

		if(action.equalsIgnoreCase("listInterested")) {
			HashMap<Integer, String> eventTags = (HashMap<Integer, String>)session.getAttribute("taggedEvents");

			ArrayList<Event> interestedEvents = edao.getEventsFromIDList(tdao.getInterestedEventIDs(eventTags));
			request.setAttribute("intevents", interestedEvents);
			forward = "/interestedEvents.jsp";
		}
		else if(action.equalsIgnoreCase("listConfirmed")) {
			HashMap<Integer, String> eventTags = (HashMap<Integer, String>)session.getAttribute("taggedEvents");

			ArrayList<Event> confirmedEvents = edao.getEventsFromIDList(tdao.getConfirmedEventIDs(eventTags));
			request.setAttribute("confevents", confirmedEvents);
			forward = "/confirmedEvents.jsp";
		}
		else if(action.equalsIgnoreCase("setConfirm")){
			int eventID = Integer.parseInt(request.getParameter("eventID"));
			
			HashMap<Integer, String> eventTags = (HashMap<Integer, String>)session.getAttribute("taggedEvents");
			eventTags.put(eventID, "Confirmed");
			session.setAttribute("taggedEvents", eventTags);
			
			tdao.setEventConfirmed(eventID, username);
			forward = "/setConfirmedEvent.jsp";	
		}
		else if(action.equalsIgnoreCase("setInterested")){
			int eventID = Integer.parseInt(request.getParameter("eventID"));
			
			HashMap<Integer, String> eventTags = (HashMap<Integer, String>)session.getAttribute("taggedEvents");
			eventTags.put(eventID, "Interested");
			session.setAttribute("taggedEvents", eventTags);			
			
			tdao.setEventInterested(eventID, username);
			forward = "/setInterestedEvent.jsp";
		}
		else if(action.equalsIgnoreCase("deleteEvent")){
			int eventID = Integer.parseInt(request.getParameter("eventID"));
			
			HashMap<Integer, String> eventTags = (HashMap<Integer, String>)session.getAttribute("taggedEvents");
			eventTags.put(eventID, "None");
			session.setAttribute("taggedEvents", eventTags);
			
			tdao.deleteEvent(eventID, username);
			forward = "/setDeletedEvent.jsp";
		}
		
				
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
	}
	
}