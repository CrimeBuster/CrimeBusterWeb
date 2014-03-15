package models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

public class Users implements Serializable 
{   
  @Id
  private int index;

  @Column(name="UserName")  
  private String userName;
  
  @Column(name="FirstName")  
  private String firstName;

  @Column(name="LastName")  
  private String lastName;
  
  @Column(name="Gender")  
  private String gender;
  
  @Column(name="Email")  
  private String email;
  
  @Column(name="Phone")  
  private int phone;
  
  @Column(name="Address")  
  private String address;
  
  @Column(name="ZipCode")  
  private int zipCode;
  
  @Column(name="Token")  
  private String token;
  
   public Users()  
  {

  }

  public Users(int index, String userName, String firstName, String lastName, String gender, String email, int phone, String address, int zipCode, String token)
  {     
     this.index = index;      
     this.userName = userName;
	 this.firstName = firstName;
	 this.lastName = lastName;
	 this.gender = gender;
	 this.email=email;
	 this.phone=phone;
	 this.address=address;
	 this.zipCode=zipCode;
	 this.token=token;
  }

 // generate getters and setters
}