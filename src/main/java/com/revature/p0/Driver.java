package com.revature.p0;

import com.revature.p0.util.AppState;
import com.revature.p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Driver {

	private static AppState app = new AppState();

	public static void main(String[] args) {

		app.startup();
		app.shutdown();
	}

	public static AppState app() {
		return app;
	}
}
