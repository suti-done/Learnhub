package com.Learnhub.dao;

public class UserAlreadyExistException extends Exception {

	public void printStackTrace()
	{
		System.out.println("user exist");
	}
	
}
