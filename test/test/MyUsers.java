package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import models.Users;

@WebService()
@Entity
@Table(name = "USERS")
@Path("/users")
//@NamedQuery(name="selectUser", query="select USERNAME, TOKEN from USERS")
public class MyUsers
{   
   private Users users;  
   public MyUsers()   
   {    
     // TODO Auto-generated constructor stub      
     users = new Users();    
   }    

   @POST    
   @Path("/save")  
   @WebMethod(operationName = "insert") 
   public String insert(@FormParam("userName") String userName, @FormParam("firstName") String firstName, @FormParam("lastName") String lastName,
						@FormParam("gender") String gender, @FormParam("email") String email, @FormParam("phone") int phone,
						@FormParam("address") String address, @FormParam("zipCode") int zipCode, @FormParam("token") String token) 
   {     
      try   
      {     
	     String password = System.getProperty("password");
         Class.forName("com.mysql.jdbc.Driver");        
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crimebusters", "root", password);

         String query = "insert into USERS"+"(UserName, FirstName, LastName, Gender, Email, Phone, Address, ZipCode, Token) VALUES"+"(?,?,?,?,?,?,?,?,?)";
     PreparedStatement st = con.prepareStatement(query);    
         st.setString(1,userName);  
		 st.setString(2,firstName);
		 st.setString(3,lastName);
		 st.setString(4,gender);
		 st.setString(5,email);
		 st.setInt(6,phone);
		 st.setString(7,address);
		 st.setInt(8, zipCode);
		 st.setString(9, token);
		 
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


   public Users getUsers() 
  {     
     return users; 
  } 
  public void setUsers(Users users) 
  {     
     this.users = users;  
  }
}