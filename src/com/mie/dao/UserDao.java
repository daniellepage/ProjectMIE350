package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mie.model.Admin;
import com.mie.model.Member;
import com.mie.model.User;
import com.mie.util.DbUtil;

public class UserDao {
	/**
	 * This class handles all of the User-related methods
	 * (add/update/delete/get).
	 */

	private Connection connection;
	static Connection currentCon = null;
	static ResultSet rs = null;

	public UserDao() {
		/**
		 * Get the database connection.
		 */
		connection = DbUtil.getConnection();
	}
	
	
	
//make this for add member which admins and users can do - takes in raw data from new accnt form
	public void addUser(User User) {
		/**
		 * This method adds a new User to the database.
		 */
		
		//OKAY SOMEHOW we need to make it loop back to ask the person to re-enter their info
		/*if (getUsernames(getAllUsers()).contains(User.getUsername())){
			
			System.out.println("Dude this username already exists, pick a new one my guy"); //NOT SURE IF THIS WORKS
		
		} else{*/
			try {
				
				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into Users(username,password,firstname,lastname,email,age,address,phoneNum,active) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				// Parameters start with 1
				preparedStatement.setString(1, User.getUsername());
				preparedStatement.setString(2, User.getPassword());
				preparedStatement.setString(3, User.getFirstName());
				preparedStatement.setString(4, User.getLastName());
				preparedStatement.setString(5, User.getEmail());
				preparedStatement.setInt(6, User.getAge());
				preparedStatement.setString(7, User.getAddress());
				preparedStatement.setString(8, User.getPhoneNum());
				preparedStatement.setString(9, "true");
				
				preparedStatement.executeUpdate();
				
				
				PreparedStatement taggedStatement = connection.prepareStatement
						("INSERT INTO TaggedEvents (username, event1, event2, event3, event4, event5, event6, event7, event8, event9, event10, event11, event12, event13) VALUES (?, 'None', 'None', 'None', 'None', 'None', 'None', 'None', 'None', 'None', 'None', 'None', 'None', 'None')");
				taggedStatement.setString(1, User.getUsername());
				taggedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	//}
	
	
	public List<String> getUsernames(List<User> userlist){ 
		
		List<String> usernamelist = new ArrayList<String>();
		
		for (User u:userlist){
			usernamelist.add(u.getUsername());
		}
	
		return usernamelist;
		
	}
	
	public List<String> getAllUsernames() {
		/**
		 * This method returns the list of all Users in the form of a List
		 * object.
		 */
		List<String> Usernames = new ArrayList<String>();
		try {
			Statement statement = connection.createStatement();
			// System.out.println("getting Users from table");
			ResultSet rs = statement.executeQuery("select username from Users");
			while (rs.next()) {
				System.out.println(rs);
				String username = rs.getString("username");
				Usernames.add(username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Usernames;
	}
	

	public void deactivateUser(String username) { 
		//
		/**
		 * This method sets a user's activity status to false.
		 */
		String query = "UPDATE Users SET active = 'false' WHERE username = '"+ username+"'";
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void reactivateUser(String username) {
	
		String query = "UPDATE Users SET active = 'true' WHERE username = '"+ username+"'";
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateUser(User User) {
		/**
		 * This method updates a User's information into the database.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update Users set firstname=?, lastname=?, email=?, age=?, address=?, phoneNum=?"
							+ " where username=?");
			// Parameters start with 1
			
			preparedStatement.setString(1, User.getFirstName());
			preparedStatement.setString(2, User.getLastName());
			preparedStatement.setString(3, User.getEmail());
			preparedStatement.setString(4, String.valueOf(User.getAge()));
			preparedStatement.setString(5, User.getAddress());
			preparedStatement.setString(7, String.valueOf(User.getPhoneNum()));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() {
		/**
		 * This method returns the list of all Users in the form of a List
		 * object.
		 */
		List<User> Users = new ArrayList<User>();
		try {
			Statement statement = connection.createStatement();
			// System.out.println("getting Users from table");
			ResultSet rs = statement.executeQuery("select * from Users");
			while (rs.next()) {
				User User = new User();
				User.setUsername(rs.getString("username"));
				User.setFirstName(rs.getString("firstname"));
				User.setLastName(rs.getString("lastname"));
				User.setEmail(rs.getString("email"));
				User.setAge(rs.getInt("age"));
				User.setAddress(rs.getString("address"));
				User.setPhoneNum(rs.getString("phoneNum"));
				User.setActive(Boolean.parseBoolean(rs.getString("active"))); 
				
				
				Users.add(User);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Users;
	}


	public User getUserByUsername(String Username) {

		User User = new User();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from Users where username=?");
			preparedStatement.setString(1, Username);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				User.setUsername(rs.getString("username"));
				User.setFirstName(rs.getString("firstname"));
				User.setLastName(rs.getString("lastname"));
				User.setEmail(rs.getString("email"));
				User.setAge(rs.getInt("age"));
				User.setAddress(rs.getString("address"));
				User.setPhoneNum(rs.getString("phoneNum"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return User;
	}
	
	
public static boolean userExists(String username) {
		
		String searchQuery = "Select * from users where username='" + username + "'";
		Statement stmt = null;

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			return(rs.next());
		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
			return(false);
		}
		
	}

public static User login(User user) {

	/**
	 * This method attempts to find the member that is trying to log in by
	 * first retrieving the username and password entered by the user.
	 */
	Statement stmt = null;

	String username = user.getUsername();
	String password = user.getPassword();

	/**
	 * Prepare a query that searches the members table in the database
	 * with the given username and password.
	 */
	String searchQuery = "select * from users where username='"
			+ username + "' AND password='" + password + "'";

	try {
		// connect to DB
		currentCon = DbUtil.getConnection();
		stmt = currentCon.createStatement();
		rs = stmt.executeQuery(searchQuery);
		boolean more = rs.next();

		/**
		 * If there are no results from the query, set the member to false.
		 * The person attempting to log in will be redirected to the home
		 * page when isValid is false.
		 */
		
		if (!more) {
			user.setValid(false);
		}
		
		/**
		 * If the query results in an database entry that matches the
		 * username and password, assign the appropriate information to
		 * the Member object.
		 */
		else if (more) {

			user.setUsername(rs.getString("username"));
			user.setFirstName(rs.getString("firstname"));
			user.setLastName(rs.getString("lastname"));
			user.setEmail(rs.getString("email"));
			user.setAge(rs.getInt("age"));
			user.setAddress(rs.getString("address"));
			user.setPhoneNum(rs.getString("phoneNum"));
			user.setAdmin(false);
			user.setActive(Boolean.parseBoolean(rs.getString("active")));
			user.setValid(true);
		}
	}

	catch (Exception ex) {
		System.out.println("Log In failed: An Exception has occurred! "
				+ ex);
	}
	/**
	 * Return the Member object.
	 */
	return user;

	}

}