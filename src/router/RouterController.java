package router;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Report;

@Path("/crimes")
public class RouterController {
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMsg(){
		
		//String output = "Jersey say : " + msg;
		
		return "HELLO KHUSHBU";
	}
	
	@GET
	@Path("/retreiveReport")
	@Produces("application/json")
	public Report getReport(){
		
		int id = 100;
		int typeId = 123;
		String message = "test";
		float latitude = 1;
		float longitude = 1;
		String resourceURL = "testURL";
		String timestamp = "1970-01-01 00:00:01";
		String userName = "testUserName";
		
		Report report = new Report(id, typeId, message, latitude, longitude, resourceURL, timestamp, userName);
		
		return report;
	}	
}
