package libraryProject;

import java.util.*;

public class StockDisplay
{

	private int stockID;
	private String title;
	private String author;
	private String publisher;
	private String volume;
	private String issue;
	private String year;
	private int borrowerID;
	
	public StockDisplay()
	{
		//empty constructor
	}
	
	
	public StockDisplay(int stockID, String title, String author, String publisher, String volume, String issue,
			String year, int borrowerID) 
	{
		//super();
		this.stockID = stockID;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.volume = volume;
		this.issue = issue;
		this.year = year;
		this.borrowerID = borrowerID;
	}
	
	
	public StockDisplay(int stockID, String title, String author, String publisher, String volume, String issue,
			String year) 
	{
		//super();
		this.stockID = stockID;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.volume = volume;
		this.issue = issue;
		this.year = year;
	}
	
	
	public int getStockID() 
	{
		return stockID;
	}
	public void setStockID(int stockID) 
	{
		this.stockID = stockID;
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public String getAuthor() 
	{
		return author;
	}
	public void setAuthor(String author) 
	{
		this.author = author;
	}
	public String getPublisher() 
	{
		return publisher;
	}
	public void setPublisher(String publisher) 
	{
		this.publisher = publisher;
	}
	public String getVolume() 
	{
		return volume;
	}
	public void setVolume(String volume) 
	{
		this.volume = volume;
	}
	public String getIssue() 
	{
		return issue;
	}
	public void setIssue(String issue) 
	{
		this.issue = issue;
	}
	public String getYear() 
	{
		return year;
	}
	public void setYear(String year) 
	{
		this.year = year;
	}
	public int getBorrowerID() 
	{
		return borrowerID;
	}
	public void setBorrowerID(int borrowerID) 
	{
		this.borrowerID = borrowerID;
	}
	
	
} // end of class
