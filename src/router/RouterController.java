package router;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

@Path("/hello")
public class RouterController {
	
	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMsg(){
		
		//String output = "Jersey say : " + msg;
		
		return "HELLO KHUSHBU";
	}
	
	
}
