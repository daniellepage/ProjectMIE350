
	package com.mie.dao;

	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

	import com.mie.model.Admin;
import com.mie.model.Event;
import com.mie.model.User;
import com.mie.model.Event;
import com.mie.util.DbUtil;

	public class TaggedEventsDao {


		private Connection connection;
		private EventDao edao;
		
		public TaggedEventsDao() {

			connection = DbUtil.getConnection();
			edao = new EventDao();
				
		}
		
		
		public HashMap<Integer, String> getEventTags(String username){

			HashMap<Integer, String> tagsMap = new HashMap<Integer, String>();
			String query = "select * from TaggedEvents where username = '" + username+ "'";

			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				ArrayList<Integer> eventIDs = new ArrayList<Integer>(edao.getAllEventIDs());

				ArrayList<String> tagResults = new ArrayList<String>();
				int numevents = eventIDs.size();
				while (rs.next()) {
					int i = 1;
					
					while (i<=numevents) {
						String result = rs.getString(i++);
						tagResults.add(result);
					}
				}

				for (int j = 0; j<numevents;j++) {

					tagsMap.put(eventIDs.get(j), tagResults.get(j));
				}
				return tagsMap;
				
				} catch (SQLException e) {
					e.printStackTrace();
				}

			return tagsMap;
		}
		
		public ArrayList<Integer> getInterestedEventIDs(HashMap<Integer,String> tags){
			ArrayList<Integer> interestedEventIDs = new ArrayList<Integer>();
			for (Map.Entry<Integer, String> event : tags.entrySet()) {
				if("Interested".equals(event.getValue())){
					interestedEventIDs.add(event.getKey());
				}
			}
			return interestedEventIDs;	
		}
		
		public ArrayList<Integer> getConfirmedEventIDs(HashMap<Integer,String> tags){
			ArrayList<Integer> confirmedEventIDs = new ArrayList<Integer>();
			for (Map.Entry<Integer, String> event : tags.entrySet()) {
				if("Confirmed".equals(event.getValue())){
					confirmedEventIDs.add(event.getKey());
				}
			}
			return confirmedEventIDs;
		}
		
		public void setEventConfirmed(int eventID, String username) {
			String query = "UPDATE TaggedEvents SET event"+eventID+" = 'Confirmed' "
					+ "WHERE username = '"+ username+"'";
			System.out.print(query);
			
			
			try {
				Statement statement = connection.createStatement();
				statement.executeUpdate(query);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void setEventInterested(int eventID, String username) {
			String query = "UPDATE TaggedEvents SET event"+eventID+" = 'Interested' "
					+ "WHERE username = '"+ username+"'";
			
			try {
				Statement statement = connection.createStatement();
				statement.executeUpdate(query);

			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void deleteEvent(int eventID, String username) {
			String query = "UPDATE TaggedEvents SET event"+eventID+" = 'None' "
					+ "WHERE username = '"+ username+"'";
			
			try {
				Statement statement = connection.createStatement();
				statement.executeUpdate(query);

			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}