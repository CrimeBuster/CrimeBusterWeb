package databaseManager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Report;

public class DatabaseService {

	// TODO connection string should be in configuration file
    Connection connection;
    String ip = "jdbc:mysql://crimebusters-dev.c0nkxyzy1tyy.us-west-2.rds.amazonaws.com:";
	String port = "3306";
	String dbName = "crimebusters";
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String userName = "crimebusters";
	String password = "cr1m3bu5t3rs";
	StringBuilder conURLStringBuilder;
	
	public DatabaseService() {
		conURLStringBuilder = new StringBuilder();
		conURLStringBuilder.append(ip);
		conURLStringBuilder.append(port);
		conURLStringBuilder.append("/");
		conURLStringBuilder.append(dbName);        
	}
	
	public Report getReport(int id) throws Exception{
		createConnection();
		ResultSet reportResultSet= runQuery(id);		
		Report reportResult = getReportFromResultSet(reportResultSet);
		closeConnection();
		return reportResult; 
	}
	
	private Report getReportFromResultSet(ResultSet reportResultSet) {
		// TODO Auto-generated method stub
		Report report = new Report();
		try {
			if (reportResultSet.next())
			{	
				report.setId(reportResultSet.getInt("Id"));
//				report.setIndex(reportResultSet.getInt("Index"));
				report.setTypeId(reportResultSet.getInt("TypeId"));
				report.setMessage(reportResultSet.getString("Message"));
				report.setLatitude(reportResultSet.getFloat("Latitude"));
				report.setLongitude(reportResultSet.getFloat("Longitude"));
				report.setResourceURL(reportResultSet.getString("ResourceURL"));
				report.setTimestamp(reportResultSet.getString("TimeStamp"));
				report.setUserName(reportResultSet.getString("UserName"));
			}
			else
			{
				report = null;
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return report;
	}

	private ResultSet runQuery(int id) throws SQLException {
		String reportQuery = buildReportQuery();
		
		PreparedStatement preparedStatement = connection.prepareStatement(reportQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet;
	}

	private String buildReportQuery() {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT ");
		queryBuilder.append("Id, ");
		queryBuilder.append("TypeId, ");
		queryBuilder.append("Message, ");
		queryBuilder.append("Latitude, ");
		queryBuilder.append("Longitude, ");
		queryBuilder.append("ResourceURL, ");
		queryBuilder.append("Timestamp, ");
		queryBuilder.append("UserName ");
		queryBuilder.append("FROM ");
		queryBuilder.append("report ");
		queryBuilder.append("WHERE ");
		queryBuilder.append("Id = ?");		
		queryBuilder.append(";");
		return queryBuilder.toString();
	}

	private void createConnection() throws Exception{
      
        
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
	
	
	
}
