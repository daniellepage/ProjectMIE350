package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mie.model.Charity;
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
				Event.setEventCode(rs.getString("eventCode"));
				Event.setCharityName(rs.getString("charityName"));
				Event.setDonationType(rs.getString("donationType"));
				Event.setEventName(rs.getString("eventName"));
				Event.setCity(rs.getString("city"));
				Event.setEventDate(rs.getDate("eventDate")); 
				Event.setCharityType(rs.getString("charityType"));
				Event.setCharID(rs.getInt("charityID"));
				
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
		System.out.println(query);
		
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Event event = new Event();
				
				event.setEventID(rs.getInt("EventID"));
				event.setEventCode(rs.getString("eventCode"));
				event.setCharityName(rs.getString("charityName"));
				event.setDonationType(rs.getString("donationType"));
				event.setEventName(rs.getString("eventName"));
				event.setCity(rs.getString("city"));
				event.setEventDate(rs.getDate("eventDate")); 
				event.setCharityType(rs.getString("charityType"));
				event.setCharID(rs.getInt("charityID"));
				
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
				Event.setEventCode(rs.getString("eventCode"));
				Event.setCharityName(rs.getString("charityName"));
				Event.setDonationType(rs.getString("donationType"));
				Event.setEventName(rs.getString("eventName"));
				Event.setCity(rs.getString("city"));
				Event.setEventDate(rs.getDate("eventDate"));
				Event.setCharityType(rs.getString("charityType"));
				Event.setCharID(rs.getInt("charityID"));
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
				Event.setEventCode(rs.getString("eventCode"));
				Event.setCharityName(rs.getString("charityName"));
				Event.setDonationType(rs.getString("donationType"));
				Event.setEventName(rs.getString("eventName"));
				Event.setCity(rs.getString("city"));
				Event.setEventDate(rs.getDate("eventDate")); 
				Event.setCharityType(rs.getString("charityType"));
				Event.setCharID(rs.getInt("charityID"));
				
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
		String locQuery = "city IN " + listToString(locations);
		String donQuery = "donationType IN " + listToString(donTypes);
		String charQuery = "charityType IN " + listToString(charTypes);
	
		String query = "SELECT * FROM Events WHERE "+locQuery+" AND "+donQuery+" AND "+charQuery;

		List<Event> Events = new ArrayList<Event>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Event Event = new Event();
				
				Event.setEventID(rs.getInt("EventID"));
				Event.setEventCode(rs.getString("eventCode"));
				Event.setCharityName(rs.getString("charityName"));
				Event.setDonationType(rs.getString("donationType"));
				Event.setEventName(rs.getString("eventName"));
				Event.setCity(rs.getString("city"));
				Event.setEventDate(rs.getDate("eventDate"));
				Event.setCharityType(rs.getString("charityType"));
				Event.setCharID(rs.getInt("charityID"));
				
				Events.add(Event);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	
	return Events;
	
	
	}
	
	public void addEvent(Event event) {
		/**
		 * This method adds a new Charity to the database.
		 */

			try {
				
				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into Events(eventName, charityName, eventDate, donationType, city, charityType, eventCode) values (?, ?, ?, ?, ?, ?, ?)");
				// Parameters start with 1
				preparedStatement.setString(1, event.getEventName());
				preparedStatement.setString(2, event.getCharityName());
				preparedStatement.setDate(3, new java.sql.Date(event.getEventDate().getTime()));
				preparedStatement.setString(4, event.getDonationType());
				preparedStatement.setString(5, event.getCity());
				preparedStatement.setString(6, event.getCharityType());
				preparedStatement.setString(7, event.getEventCode());

				preparedStatement.executeUpdate();
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			String newColQuery = "ALTER TABLE TaggedEvents ADD "+ event.getEventCode()+ " VARCHAR(20)";
			System.out.println(newColQuery);
			try {
				Statement statement1 = connection.createStatement();
				statement1.executeUpdate(newColQuery);

			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	public List<String> getAllCodes() {
		/**
		 * This method returns the list of all Users in the form of a List
		 * object.
		 */
		List<String> Codes = new ArrayList<String>();
		try {
			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("select eventCode from Events");
			while (rs.next()) {
				System.out.println(rs);
				String code = rs.getString("eventCode");
				Codes.add(code);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Codes;
	}
	
	public List<Event> getEventsByCharity(int charID){
		
		String query = "SELECT * FROM Events WHERE charityID = " +charID;
		
		List<Event> Events = new ArrayList<Event>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				Event Event = new Event();
				
				Event.setEventID(rs.getInt("EventID"));
				Event.setEventCode(rs.getString("eventCode"));
				Event.setCharityName(rs.getString("charityName"));
				Event.setDonationType(rs.getString("donationType"));
				Event.setEventName(rs.getString("eventName"));
				Event.setCity(rs.getString("city"));
				Event.setEventDate(rs.getDate("eventDate"));
				Event.setCharityType(rs.getString("charityType"));
				Event.setCharID(rs.getInt("charityID"));
				
				Events.add(Event);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	
	return Events;
		
		
	}
	
	
}