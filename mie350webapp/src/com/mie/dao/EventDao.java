package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mie.model.Admin;
import com.mie.model.Event;
import com.mie.model.Member;
import com.mie.model.Event;
import com.mie.model.Event;
import com.mie.util.DbUtil;

public class EventDao {
	
	/**
	 * This class handles the Event objects ???
	 */
	
	
	private Connection connection;

	public EventDao() {
		/**
		 * Get the database connection.
		 */
		connection = DbUtil.getConnection();
	}
	
	public List<Event> getAllEvents() {
		/**
		 * This method returns the list of all Events in the form of a List
		 * object.
		 */
		List<Event> Events = new ArrayList<Event>();
		try {
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery("select * from Events");
			while (rs.next()) {
				Event Event = new Event();
				
				Event.setEventID(rs.getInt("EventID"));
				Event.setCharityName(rs.getString("charityName"));
				Event.setDonationType(rs.getString("donationType"));
				Event.setEventName(rs.getString("eventName"));
				Event.setCity(rs.getString("city"));
				Event.setEventDate(rs.getDate("eventDate")); //yáll we gotta make sure this is real
				
				Events.add(Event);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Events;
	}
	
	public ArrayList<Event> getEventsFromIDList(ArrayList<Integer> eventIDs){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from Events where eventID IN (");
		for (int eventID:eventIDs) {
			sb.append(eventID);
			sb.append(", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(")");
		String query = sb.toString();
		
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Event event = new Event();
				
				event.setEventID(rs.getInt("EventID"));
				event.setCharityName(rs.getString("charityName"));
				event.setDonationType(rs.getString("donationType"));
				event.setEventName(rs.getString("eventName"));
				event.setCity(rs.getString("city"));
				event.setEventDate(rs.getDate("eventDate")); 
				
				events.add(event);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
	
	public ArrayList<Integer> getAllEventIDs (){
		
		ArrayList<Integer> EventIDs = new ArrayList<Integer>();
		try {
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery("select eventID from Events");
			while (rs.next()) {
				int eventID = rs.getInt("EventID");
				
				EventIDs.add(eventID);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return EventIDs;
	}
	
	public Event getEventByEventname(String EventName) {

		Event Event = new Event();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from Events where EventName=?");
			preparedStatement.setString(1, EventName);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				Event.setEventID(rs.getInt("EventID"));
				Event.setCharityName(rs.getString("charityName"));
				Event.setDonationType(rs.getString("donationType"));
				Event.setEventName(rs.getString("eventName"));
				Event.setCity(rs.getString("city"));
				Event.setEventDate(rs.getDate("eventDate")); //yáll we gotta make sure this is real
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Event;
	}
	
	public List<Event> getEventByKeyword(String keyword) {
		/**
		 * This method retrieves a list of Events that matches the keyword
		 * entered by the user.
		 */
		List<Event> Events = new ArrayList<Event>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from Events where EventName LIKE ? OR EventCategory LIKE ? OR city LIKE ? or charityName LIKE ?");

			preparedStatement.setString(1, "%" + keyword + "%");
			preparedStatement.setString(2, "%" + keyword + "%");
			preparedStatement.setString(3, "%" + keyword + "%");
			preparedStatement.setString(4, "%" + keyword + "%");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Event Event = new Event();
				
				Event.setEventID(rs.getInt("EventID"));
				Event.setCharityName(rs.getString("charityName"));
				Event.setDonationType(rs.getString("donationType"));
				Event.setEventName(rs.getString("eventName"));
				Event.setCity(rs.getString("city"));
				Event.setEventDate(rs.getDate("eventDate")); //yáll we gotta make sure this is real
				
				Events.add(Event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Events;
	}
	
	
	public static String listToString(List<String> lst) {
		String str = "(";
		for (String i:lst) {
			str=str+"'"+i+"', ";
		}
		str = str.substring(0, str.length()-2);
		str+=")";
		return str;
	}
	
	
	public List<Event> getEventSearchResults(List<String>locations, List<String> donTypes, List<String>charTypes){
		System.out.println("test");
		String locQuery = "city IN " + listToString(locations);
		String donQuery = "donationType IN " + listToString(donTypes);
		String charQuery = "charityType IN " + listToString(charTypes);
	
		String query = "SELECT * FROM Events WHERE "+locQuery+" AND "+donQuery+" AND "+charQuery;
		System.out.println(query);
		List<Event> Events = new ArrayList<Event>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Event Event = new Event();
				
				Event.setEventID(rs.getInt("EventID"));
				Event.setCharityName(rs.getString("charityName"));
				Event.setDonationType(rs.getString("donationType"));
				Event.setEventName(rs.getString("eventName"));
				Event.setCity(rs.getString("city"));
				Event.setEventDate(rs.getDate("eventDate"));
				
				Events.add(Event);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	
	return Events;
	
	
	}
	
}