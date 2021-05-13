package com.revature.p0.daos;

import com.revature.p0.models.AppUser;
import com.revature.p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	public void testConnection() {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from users";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt("user_id"));
				System.out.println("USERNAME: " + rs.getString("username"));
				System.out.println("PASSWORD: " + rs.getString("password"));
				System.out.println("EMAIL: " + rs.getString("email"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fetches and returns AppUser from database whose credentials match the identifier (email or username) and password
	 * @param identifier email or username
	 * @param password password
	 * @return AppUser if found, null otherwise
	 */
	public AppUser findByIdentifierAndPassword(String identifier, String password, String type) {
		AppUser foundUser = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM users WHERE " + type + " = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, identifier);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				foundUser = new AppUser();
				foundUser.setId(rs.getInt("user_id"));
				foundUser.setUsername(rs.getString("username"));
				foundUser.setPassword(rs.getString("password"));
				foundUser.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return foundUser;
	}

	/**
	 * Checks database to see whether username or email is already taken by another user
	 * @param identifier username or email to be checked
	 * @param type accepts only "email" or "password" to indicate which it is
	 * @return true if username/email is taken, false if not
	 */
	public boolean isUserIdentifierAvailable(String identifier, String type) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM users WHERE " + type + " = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, identifier);

			ResultSet rs = pstmt.executeQuery();
			return !rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	public void insertUser(AppUser appUser) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "INSERT INTO users (username, password, email) VALUES (?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "user_id" });
			pstmt.setString(1, appUser.getUsername());
			pstmt.setString(2, appUser.getPassword());
			pstmt.setString(3, appUser.getEmail());

			if (pstmt.executeUpdate() != 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				while (rs.next()) {
					appUser.setId(rs.getInt("user_id"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(AppUser appUser) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "UPDATE users " +
						 "SET username = ?, password = ?, email = ?" +
						 "WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, appUser.getUsername());
			pstmt.setString(2, appUser.getPassword());
			pstmt.setString(3, appUser.getEmail());
			pstmt.setInt(4, appUser.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
