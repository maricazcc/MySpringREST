package it.maricazocco.MySpringREST.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;


public class MyClass {
	 private int id;
	 
	 //@NotBlank
	 //@Size(min = 4, max = 8)
	 //@Email
	 //@Pattern(regexp="[A-Za-z0-9]")
	 private String myString;
	 
	 @NotNull
	 //@Range(min = 111, max = 999)
	 private int myInt;
	
	
	public MyClass() {}
	

	public MyClass(String myString, @NotNull int myInt) {
		super();
		this.myString = myString;
		this.myInt = myInt;
	}


	public MyClass(int id, String myString, int myInt) {
		super();
		this.id = id;
		this.myString = myString;
		this.myInt = myInt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	@Override
	public String toString() {
		return "MyClass [id=" + id + ", myString=" + myString + ", myInt=" + myInt + "]";
	}
	
		

}
