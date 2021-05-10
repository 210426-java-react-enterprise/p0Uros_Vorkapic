package com.revature.p0.daos;

import com.revature.p0.models.UserInfo;
import com.revature.p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDAO {

	/**
	 * Checks whether there is already a profile for a designated user_id
	 * @return true if profile exists, false otherwise
	 */
	public boolean doesProfileExist(int id) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT user_id FROM user_info WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void insertNewProfileInfo(UserInfo profile) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "INSERT INTO user_info VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, profile.getId());
			pstmt.setString(2, profile.getfName());
			pstmt.setString(3, profile.getlName());
			pstmt.setString(4, profile.getDob());
			pstmt.setString(5, profile.getStreet());
			pstmt.setString(6, profile.getCity());
			pstmt.setString(7, profile.getState());
			pstmt.setInt(8, profile.getPostalCode());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editExistingProfile(UserInfo profile) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "UPDATE user_info" +
						 "SET first_name = ?, last_name = ?, dob = ?, street = ?, city = ?, state = ?, postal_code = ?" +
						 "WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(8, profile.getId());
			pstmt.setString(1, profile.getfName());
			pstmt.setString(2, profile.getlName());
			pstmt.setString(3, profile.getDob());
			pstmt.setString(4, profile.getStreet());
			pstmt.setString(5, profile.getCity());
			pstmt.setString(6, profile.getState());
			pstmt.setInt(7, profile.getPostalCode());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getExistingProfile(UserInfo profile) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "UPDATE user_info" +
					"SET first_name = ?, last_name = ?, dob = ?, street = ?, city = ?, state = ?, postal_code = ?" +
					"WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(8, profile.getId());
			pstmt.setString(1, profile.getfName());
			pstmt.setString(2, profile.getlName());
			pstmt.setString(3, profile.getDob());
			pstmt.setString(4, profile.getStreet());
			pstmt.setString(5, profile.getCity());
			pstmt.setString(6, profile.getState());
			pstmt.setInt(7, profile.getPostalCode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
