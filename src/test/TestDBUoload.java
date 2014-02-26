package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

public class TestDBUoload {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	@Test
	public void test() throws SQLException, IllegalAccessException,
			InstantiationException, ClassNotFoundException {
		// fail("Not yet implemented");

		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "test";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = System.getProperty("password");
		;

		try {
			Class.forName(driver).newInstance();
			connect = DriverManager.getConnection(url + dbName, userName,
					password);

			// Test that the connection is not null
			assertNotNull(connect);

			// Insert a row into the "report" table
			preparedStatement = connect
					.prepareStatement("insert into  report (description, locationX, locationY, victim_name, cr_ts)"
							+ "values ( ?, ?, ?, ?, ?)");

			Random rand = new Random();
			int max = 100;
			int min = 1;
			int randomNum = rand.nextInt((max - min) + 1) + min;
			String testName = "TestName" + randomNum;
			String testDesc = "TestDesc" + randomNum;

			preparedStatement.setString(1, testDesc);
			preparedStatement.setInt(2, 22);
			preparedStatement.setInt(3, 22);
			preparedStatement.setString(4, testName);
			preparedStatement.setTimestamp(5,
					new Timestamp(new Date().getTime()));

			// Check number of rows before inserting a new row
			int countBeforeInsert = 0;
			String countRows = "select count(*) as countRow from report";
			statement = connect.createStatement();
			resultSet = statement.executeQuery(countRows);
			while (resultSet.next()) {
				countBeforeInsert = resultSet.getInt("countRow");
			}

			// Insert a new row
			preparedStatement.executeUpdate();

			// Check number of rows after inserting a new row
			int countAfterInsert = 0;
			resultSet = statement.executeQuery(countRows);
			while (resultSet.next()) {
				countAfterInsert = resultSet.getInt("countRow");
			}

			// Test if the record was inserted successfully
			assertEquals(countBeforeInsert + 1, countAfterInsert);

			// Retrieve and check the values for the inserted row
			preparedStatement = connect
					.prepareStatement("select * from report where victim_name = ?");
			preparedStatement.setString(1, testName);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int locX = resultSet.getInt("locationX");
				int locY = resultSet.getInt("locationY");
				String victim = resultSet.getString("victim_name");
				String desc = resultSet.getString("description");

				// Test if the retrieved record is the one that is inserted
				assertEquals(22, locX);
				assertEquals(22, locY);
				assertEquals(testName, victim);
				assertEquals(testDesc, desc);

			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}

				if (connect != null) {
					connect.close();
				}
			} catch (Exception e) {

			}

		}
	}

}
