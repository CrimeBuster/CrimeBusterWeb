package models;

import java.io.Serializable;
//import javax.persistence.Column;
//import javax.persistence.Id;

public class ReportType implements Serializable 
{   
  //@Id
  private int index;

 // @Column(name="Id")  
  private int id;
  
  //@Column(name="Name")  
  private String name;

  
   public ReportType()  
  {

  }

  public ReportType(int index, int id, String name)
  {     
     this.index = index;      
     this.id = id;  
	 this.name = name;
  }

 // generate getters and setters
}