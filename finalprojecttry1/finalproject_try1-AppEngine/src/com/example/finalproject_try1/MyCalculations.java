package com.example.finalproject_try1;
import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Key;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

	/**
	 * Place entity.
	 *
	*/

	@Entity
	public class MyCalculations {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Key key;

	  private String placeId;

	  private String name;
<<<<<<< HEAD
	  
	  //added
	  private String email;
=======
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc

	  private String address;

	  private GeoPt location;
	  
	  private double midpt_lat;
	  private double midpt_lon;
	  
	  public Key getKey() {
	    return key;
	  }
	  public void setKey(Key k) {
		this.key = k;
	  }

	  public String getPlaceId() {
	    return placeId;
	  }

	  public void setplaceID(String placeId) {
	    this.placeId = placeId;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

<<<<<<< HEAD
	  //added
	  
	  public String getEmail() {
		    return email;
		  }

      public void setEmail(String email) {
		    this.email = email;
		  }
	  
=======
>>>>>>> e83148c69b6179eee336db2be81a0fdb78299acc
	  public String getAddress() {
	    return address;
	  }

	  public void setAddress(String address) {
	    this.address = address;
	  }

	  public GeoPt getLocation() {
	    return location;
	  }

	  public void setLocation(GeoPt location) {
	    this.location = location;
	  }
	  
	  public double getMidpointLat() {
		  return midpt_lat;
	  }
	  public double getMidpointLon() {
		  return midpt_lon;
	  }
	/*  public void setMidpoint(double lat, double lon) {
		  this.midpt_lat = lat;
		  this.midpt_lon = lon;
	  }*/
	  public void setMidpointLat(double lat) {
		  this.midpt_lat = lat;		  
	  }
	  public void setMidpointLon(double lon) {
		  this.midpt_lon = lon;		  
	  }


	}

