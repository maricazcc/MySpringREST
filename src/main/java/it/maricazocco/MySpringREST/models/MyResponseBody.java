package it.maricazocco.MySpringREST.models;

import java.util.Date;


public class MyResponseBody {
	
	Date myDate;
	String myString;

	 

	 public MyResponseBody(String myString) {

	  this.myDate = new Date();

	  this.myString = myString;

	 }
 

	 public Date getMyDate() {

	  return myDate;

	 }

	 public void setMyDate(Date myDate) {

	  this.myDate = myDate;

	 }

	 

	 public String getMyString() {

	  return myString;

	 }

	 public void setMyString(String myString) {

	  this.myString = myString;

	 }


}
