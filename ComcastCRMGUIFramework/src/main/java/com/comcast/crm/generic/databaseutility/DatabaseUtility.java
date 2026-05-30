package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {

	Connection conn = null;

	public void getDbConnection(String url, String username, String password) throws SQLException {

		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			conn = DriverManager.getConnection(url, username, password);
			System.out.println("DB Connection established");
		} catch (SQLException e) {
			System.out.println("Failed to establish DB connection");
			e.printStackTrace();
			throw e; // Exception Propagation
		}

	}

	public void getDbConnection() throws SQLException {

		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root2527");
			System.out.println("DB Connection established (default)");
		} catch (SQLException e) {
			System.out.println("Failed to establish DB connection");
			e.printStackTrace();
			throw e;
		}

	}

	public void closeDbConnection() throws SQLException {

		try {
			if (conn != null) {
				conn.close();
				System.out.println("DB Connection closed");
			}
		} catch (SQLException e) {
			System.out.println("Failed to close DB connection");
			e.printStackTrace();
			throw e;
		}

	}

	public ResultSet executeSelectQuery(String query) throws SQLException {
		ResultSet result = null;

		try {
			Statement st = conn.createStatement();
			result = st.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("❌ Failed to execute SELECT query");
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public int executeNonSelectQuery(String query) throws SQLException {
		int result = 0;

		try {
			Statement st = conn.createStatement();
			result = st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Failed to execute Non-Select query");
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public void executeAndGetData(String query, int colIndex, String expData) throws SQLException {

		Statement st = null;
		ResultSet result = null;

		try {
			// Step-3: Create Statement
			st = conn.createStatement();

			// Step_4: Execute query/Update query
			result = st.executeQuery(query);
			boolean flag = false;
			while (result.next()) {
				String actData = result.getString(colIndex);
				if (actData.contains(expData)) {
					flag = true;
					break;
				}
			}
			if (flag == true) {
				System.out.println("===Data is verified===");
			} else {
				System.out.println("===Data is not present===");
			}
		} catch (SQLException e) {
			System.out.println("Failed to execute verification query");
			e.printStackTrace();
			throw e;
		} finally {
			if (result != null)
				result.close();
			if (st != null)
				st.close();
		}
	}
}
