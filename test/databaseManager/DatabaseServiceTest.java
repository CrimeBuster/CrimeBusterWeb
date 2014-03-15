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
	String dbName = "crimebusters";
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String userName = "crimebusters";
	String password = "cr1m3bu5t3rs";
	StringBuilder conURLStringBuilder;

	DatabaseService testDbService;
	int testId = 777;
	int testIndex = 1000;
	String testMessage = "testMessage";
	float testLatitude = 7f;
	float testLongitude = 7f;
	String testResourceURL = "testResourceURL";
	String testTimeStamp = "testTimeStamp";
	String testUserName = "testUserName";
	int testTypeId = 7777;
	
	
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
		//String seedTestDataQuery = "INSERT INTO report (Id, Index, Message, Latitude, Longitude, ResourceURL, TimeStamp, UserName, TypeId)";
		//seedTestDataQuery = seedTestDataQuery + " VALUES (777, 1000, 'testMessage', 1.0, 1.0, 'testResourceURL', 'testTimeStamp', 'testUserName', 7777);";
		String seedTestDataQuery = "INSERT INTO reporttype (Id, Name) VALUES (1,'hello');";
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("ALTER TABLE reporttype MODIFY COLUMN Index INT(20) AUTO_INCREMENT;");
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement("ALTER TABLE reporttype AUTO_INCREMENT = 2335;");
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(seedTestDataQuery);
			preparedStatement.executeUpdate();
			
			/*preparedStatement.setInt(1, testId);
			preparedStatement.setInt(2, testIndex);
			preparedStatement.setString(3, testMessage);
			preparedStatement.setFloat(4, testLatitude);
			preparedStatement.setFloat(5, testLongitude);
			preparedStatement.setString(6, testResourceURL);
			preparedStatement.setString(7, testTimestamp);
			preparedStatement.setString(8, testUserName);
			preparedStatement.setInt(9, testTypeId)*/;
					
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		closeConnection();
	}
	
	private void removeSeedTestData() throws Exception{
		createConnection();
		String removeTestDataQuery = "DELETE * report WHERE Id = " + testId + ";"; 
		
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(removeTestDataQuery);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
	}
	
	
}
