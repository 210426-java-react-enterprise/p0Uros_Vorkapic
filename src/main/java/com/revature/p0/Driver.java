package com.revature.p0;

import com.revature.p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from p0.users";
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
}
