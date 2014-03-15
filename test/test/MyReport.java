package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.jws.WebMethod;
import javax.jws.WebService;
//import javax.persistence.Entity;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
//import javax.persistence.Table;

import models.Report;

@WebService()
//@Entity
//@Table(name = "REPORT")
@Path("/report")
//@NamedQuery(name="selectReport", query="select ID, MESSAGE from REPORT")
public class MyReport 
{   
   private Report report;  
   public MyReport()   
   {    
     // TODO Auto-generated constructor stub      
     report = new Report();    
   }    

   @POST    
   @Path("/save")  
   @WebMethod(operationName = "insert") 
   public String insert(@FormParam("name") String name, @FormParam("message") String message, @FormParam("latitude") int latitude,
						@FormParam("longitude") int longitude, @FormParam("resourceURL") String resourceURL, @FormParam("timestamp") String timestamp,
						@FormParam("userName") String userName) 
   {     
      try   
      {     
	     String password = System.getProperty("password");
         Class.forName("com.mysql.jdbc.Driver");        
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crimebusters", "root", password);

         String query = "insert into REPORT"+"(Name, Message, Latitude, Longitude, ResourceURL, Timestamp, UserName) VALUES"+"(?,?,?,?,?,?,?)";
     PreparedStatement st = con.prepareStatement(query);    
         st.setString(1,name);  
		 st.setString(2,message);
		 st.setInt(3,latitude);
		 st.setInt(4,longitude);
		 st.setString(5,resourceURL);
		 st.setString(6,timestamp);
		 st.setString(7,userName);
		 
     st.executeUpdate();
      }     
      catch (Exception e)   
      {     
         System.out.println(e.getMessage());    
      } 
      return"Record inserted successfully";
   }    
   
    @GET
    @Path("/retrieve")
    @Produces("text/html")
    @WebMethod(operationName = "retrieve")
    public String retrieve() 
    {
        ResultSet rs = null;
        String details = ""; 
		String password = System.getProperty("password");
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crimebusters", "root", password);

            String query = "select ID,MESSAGE from CUSTOMER";

            PreparedStatement st = con.prepareStatement(query);
            rs = st.executeQuery();

            details = "<html><body>"; 
            details = details + "<table border=1>";
            details = details + "<tr><td><Strong>Id </Strong></td>" +
                                    "<td><Strong>Message </Strong></td>" + "</tr>";
            while (rs.next()) 
            {
                details = details + "<tr><td>" + rs.getInt("Id") + "</td>" +
                                        "<td>" + rs.getString("Message") + "</td></tr>";
            }
            details += "</table></body></html>"; 
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }   
        return details;
    }

  public Report getReport() 
  {     
     return report; 
  } 
  public void setReport(Report report) 
  {     
     this.report = report;  
  }
}