package com.revature.p0.daos;

import com.revature.p0.models.UserProfile;
import com.revature.p0.util.ConnectionFactory;

import java.sql.*;

public class ProfileDAO {

	/**
	 * Checks whether there is already a profile for a designated user_id
	 * @return true if profile exists, false otherwise
	 */
	public boolean doesProfileExist(int id) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM user_info WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Inserts UserInfo profile into database
	 * @param profile data to be added to database
	 */
	public void insertNewProfile(UserProfile profile) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "INSERT INTO user_info VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, profile.getId());
			pstmt.setString(2, profile.getfName());
			pstmt.setString(3, profile.getlName());
			pstmt.setString(4, profile.getStreet());
			pstmt.setString(5, profile.getCity());
			pstmt.setString(6, profile.getState());
			pstmt.setString(7, profile.getPostalCode());
			pstmt.setString(8, profile.getDob());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates existing profile by overwriting database entry where ids match
	 * @param profile new user_info entry to overwrite existing entry in database
	 */
	public void updateProfile(UserProfile profile) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "UPDATE user_info " +
						 "SET first_name = ?, last_name = ?, street_address = ?, city = ?, state = ?, postal_code = ?, date_of_birth = ? " +
						 "WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, profile.getfName());
			pstmt.setString(2, profile.getlName());
			pstmt.setString(3, profile.getStreet());
			pstmt.setString(4, profile.getCity());
			pstmt.setString(5, profile.getState());
			pstmt.setString(6, profile.getPostalCode());
			pstmt.setString(7, profile.getDob());
			pstmt.setInt(8, profile.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finds a profile in the database whose id matches param id
	 * @param id to compare to database user id
	 * @return returns profile from database if it exists, null otherwise
	 */
	public UserProfile fetchProfile(int id) {
		UserProfile profile = null;

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM user_info WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				profile = new UserProfile();
				profile.setId(rs.getInt("user_id"));
				profile.setfName(rs.getString("first_name"));
				profile.setlName(rs.getString("last_name"));
				profile.setDob(rs.getString("date_of_birth"));
				profile.setStreet(rs.getString("street_address"));
				profile.setCity(rs.getString("city"));
				profile.setState(rs.getString("state"));
				profile.setPostalCode(rs.getString("postal_code"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return profile;
	}
}
