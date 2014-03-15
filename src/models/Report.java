package models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

public class Report implements Serializable {
	@Id
	private int index;

	@Column(name = "TypeId")
	private int typeId;
	
	@Column(name = "Id")
	private int id;

	@Column(name = "Message")
	private String message;

	@Column(name = "Latitude")
	private float latitude;

	@Column(name = "Longitude")
	private float longitude;

	@Column(name = "ResourceURL")
	private String resourceURL;

	@Column(name = "Timestamp")
	private String timestamp;

	@Column(name = "UserName")
	private String userName;

	public Report() {

	}

	public Report(int index, int id, String message,
			float latitude, float longitude, String resourceURL,
			String timestamp, String userName) {
		this.index = index;
		this.id = id;
		this.message = message;
		this.latitude = latitude;
		this.longitude = longitude;
		this.resourceURL = resourceURL;
		this.timestamp = timestamp;
		this.userName = userName;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}	
	

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getResourceURL() {
		return resourceURL;
	}

	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// generate getters and setters
}