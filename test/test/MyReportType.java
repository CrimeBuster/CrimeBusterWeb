package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.jws.WebMethod;
import javax.jws.WebService;
//import javax.persistence.Entity;
//import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import models.ReportType;

@WebService()
//@Entity
//@Table(name = "REPORTTYPE")
@Path("/reporttype")
//@NamedQuery(name="selectReportType", query="select ID, NAME from REPORTTYPE")
public class MyReportType 
{   
   private ReportType reportType;  
   public MyReportType()   
   {    
     // TODO Auto-generated constructor stub      
     reportType = new ReportType();    
   }    

   @POST    
   @Path("/save")  
   @WebMethod(operationName = "insert") 
   public String insert(@FormParam("name") String name) 
   {     
      try   
      {     
	     String password = System.getProperty("password");
         Class.forName("com.mysql.jdbc.Driver");        
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crimebusters", "root", password);

         String query = "insert into REPORT"+"(Name) VALUES"+"(?)";
     PreparedStatement st = con.prepareStatement(query);    
         st.setString(1,name);  
			 
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

            String query = "select ID, NAME from REPORTTYPE";

            PreparedStatement st = con.prepareStatement(query);
            rs = st.executeQuery();

            details = "<html><body>"; 
            details = details + "<table border=1>";
            details = details + "<tr><td><Strong>Id </Strong></td>" +
                                    "<td><Strong>Name </Strong></td>" + "</tr>";
            while (rs.next()) 
            {
                details = details + "<tr><td>" + rs.getInt("Id") + "</td>" +
                                        "<td>" + rs.getString("Name") + "</td></tr>";
            }
            details += "</table></body></html>"; 
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }   
        return details;
    }
  public ReportType getReportType() 
  {     
     return reportType; 
  } 
  public void setReportType(ReportType reportType) 
  {     
     this.reportType = reportType;  
  }
}