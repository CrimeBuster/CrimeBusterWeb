package databaseManager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Report;

import org.junit.Before;
import org.junit.Test;

public class DatabaseServiceTest {
	// TODO connection string should be in configuration file
    Connection connection;
    String ip = "jdbc:mysql://crimebusters-dev.c0nkxyzy1tyy.us-west-2.rds.amazonaws.com:";
	String port = "3306";
	String dbName = "crimebustersapp";
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String userName = "crimebusters";
	String password = "cr1m3bu5t3rs";
	StringBuilder conURLStringBuilder;

	DatabaseService testDbService;
	int testId = 7777;
	int testIndex = 1000;
	String testMessage = "testMessage";
	float testLatitude = 7f;
	float testLongitude = 7f;
	String testResourceURL = "testResourceURL";
	String testTimeStamp = "testTimeStamp";
	String testUserName = "testUserName";
	String testFirstName = "testFirstName";
	String testLastName= "testLastName";
//	int testTypeId = 7777;
	
	
	@Before
	public void setup(){
		testDbService = new DatabaseService();
	}
	
	@Test
	public void testGetReportNull(){
		Report testReport = null;
		try {
			testReport = testDbService.getReport(testId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNull(testReport);
	}
	
	@Test
	public void testGetReport_CorrectDataIsAttained(){
		try {
			
			seedTestData();		
			removeSeedTestData();

			/*Report testReport = testDbService.getReport(testId);
			assertEquals(testReport.getId(),testId);
			assertEquals(testReport.getMessage(),testMessage);
			assertEquals(testReport.getLatitude(),testLatitude, 0.001);
			assertEquals(testReport.getLongitude(),testLongitude,0.001);
			assertEquals(testReport.getResourceURL(),testResourceURL);
			assertEquals(testReport.getTimestamp(),testTimeStamp);
			assertEquals(testReport.getUserName(),testUserName);
			
			
			removeSeedTestData();*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createConnection() throws Exception{
		conURLStringBuilder = new StringBuilder();
		conURLStringBuilder.append(ip);
		conURLStringBuilder.append(port);
		conURLStringBuilder.append("/");
		conURLStringBuilder.append(dbName);  
		try {
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(conURLStringBuilder.toString(), userName,
					password);
		} catch (Exception e) {
			throw e;
		} 
	}
	
	private void closeConnection() throws SQLException{
		connection.close();
	}
	
	private void seedTestData() throws Exception{
		createConnection();
		assertNotNull(connection);
		String preSeedTestDataQuery = "INSERT INTO report (Id, TypeId, Message, Latitude, Longitude, ResourceURL, TimeStamp, UserName )";
		preSeedTestDataQuery = preSeedTestDataQuery + " VALUES (0, 7777, 'testMessage', 1.0, 1.0, 'testResourceURL', '1970-01-01 00:00:01', 'testUserName');";

		//		String preSeedTestDataQuery1 = "ALTER TABLE users ALTER column Email SET DEFAULT 'abc@yahoo.com';";
//		String preSeedTestDataQuery2 = "ALTER TABLE users ALTER column Index SET DEFAULT 1;";
//		String preSeedTestDataQuery3="ALTER TABLE users AUTO_INCREMENT=1;";
//		String seedTestDataQuery = "INSERT INTO users (UserName, FirstName, LastName, Gender, Phone, Address, ZipCode, Token) "
//				+ "VALUES ('testUserName', 'testFirstName', 'testLasName', 'testGender', 123, 'testAddress', 123, 'testToken');";
//		String seedTestDataQuery = "INSERT INTO users (UserName) VALUES ('testUserName');";	
				
//		String preSeedTestDataQuery2 = "ALTER TABLE reporttype ALTER column Index SET DEFAULT 1;";
		String seedTestDataQuery = "INSERT INTO reporttype (Id, Name) VALUES (7777, 'testName')";
		

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(preSeedTestDataQuery);
			/*preparedStatement.setInt(1, testId);
			preparedStatement.setInt(2, testIndex);
			preparedStatement.setString(3, testMessage);
			preparedStatement.setFloat(4, testLatitude);
			preparedStatement.setFloat(5, testLongitude);
			preparedStatement.setString(6, testResourceURL);
			preparedStatement.setString(7, testTimestamp);
			preparedStatement.setString(8, testUserName);
			preparedStatement.setInt(9, testTypeId);*/
			int test1 = preparedStatement.executeUpdate();
			assertEquals(1,test1);
			
//			preparedStatement = connection.prepareStatement(preSeedTestDataQuery2);
//			int test2 = preparedStatement.executeUpdate();
//			assertEquals(0, test2);
			
//			preparedStatement = connection.prepareStatement(preSeedTestDataQuery3);
//			int test3 = preparedStatement.executeUpdate();
//			assertEquals(0, test3);
			
			preparedStatement = connection.prepareStatement(seedTestDataQuery);
			int test4 = preparedStatement.executeUpdate();
			assertEquals(1,test4);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		closeConnection();
	}
	
	private void removeSeedTestData() throws Exception{
		createConnection();
		String removeTestDataQuery1 = "DELETE from reporttype where id = 7777";
		String removeTestDataQuery2 = "DELETE from report WHERE TypeId = 7777"; 
		
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(removeTestDataQuery1);
			int test1 = preparedStatement.executeUpdate();
			assertEquals(1, test1);
			
			preparedStatement = connection.prepareStatement(removeTestDataQuery2);
			int test2 = preparedStatement.executeUpdate();
			assertEquals(1, test2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
	}
	
	
}
