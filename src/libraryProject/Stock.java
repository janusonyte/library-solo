package libraryProject;

import java.util.*;

public class Stock 
{

	public int libraryNum;
	public String title;
//	protected Member member;
	
	public Stock() 
	{
		//empty constructor
	}
	public Stock(int libraryNum, String title, Member member) 
	{
		
		this.libraryNum = libraryNum;
		this.title = title;
//		this.member = member;
	}
	public int getLibraryNum() {
		return libraryNum;
	}
	public void setLibraryNum(Integer libraryNum) {
		this.libraryNum = libraryNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

//	public Member getMember() 
//	{
//		return member;
//	}
	
	
	
	//public abstract void borrow(Member member);
	
	
	//public abstract void returnStock(Member member);
	
	
	// DateTime returnDate
	
} // end of class
