package libraryProject;

import java.util.ArrayList;
import java.util.regex.*;

public class Controller 
{
	private boolean valid = false;
	private static Controller controller;
	private Library library;
	
	private Controller()
	{
		library = Library.getInstance();
	}
	
	public static Controller getInstance() 
	{
        if(controller == null) 
        {
        	controller = new Controller();
        }      
        return controller;
    }
	
	
	public boolean sanitiseInput(String fname, String lname, String uname, String pword)

	{
		System.out.println(fname + lname + uname + pword);
		
		String regex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
		String regexOne = "^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String regexTwo = "^(?=.*\\d).{4,8}$";
		boolean a = Pattern.matches(regex, fname);
		boolean b = Pattern.matches(regex, lname);
		boolean c = Pattern.matches(regexOne, uname);
		boolean d = Pattern.matches(regexTwo, pword);
		
		if(a && b && c && d == true)
		{
			return true;
		}
		else 
		{
			System.out.println("Please check if fields are filled correctly");
			return false;
			
		}
		
		
		//password needs to be between 4-8 digits long and include 1 number
	}
	
	

	
	public void setUser(String name, int yob, String email, String pw, String street, String town, String pc)
	{
		library.setConnection();
		library.setUser(name, yob, email, pw, street, town, pc);
	}
	
	public void addBook(String title, String author, String publisher, String year)
	{
		library.setConnection();
		library.addBook(title, author, publisher, year);
	}
	
	public void addJournal(String title, String volume, String issue, String year)
	{
		library.setConnection();
		library.addJournal(title, volume, issue, year);
	}
	
	public boolean checkUser(String email, String pword)
	{
		library.setConnection();
		boolean userInDb = library.checkUser(email, pword);
		return userInDb;
	}
	
	public boolean getValid()
	{
		return this.valid;
		
	}
	
	public Member getUser(String email, String pword)
	{
		return library.getUser(email, pword);
	}
	
	public ArrayList<StockDisplay> displayStock()
	{
		library.setConnection();
		return library.displayStock();
	}
	
	public ArrayList<String> getColumns()
	{
		library.setConnection();
		return library.showColumns();
	}
	
	public ArrayList<StockDisplay> searchStock(String input)
	{
		library.setConnection();
		return library.searchStock(input);
	}
	
	public ArrayList<StockDisplay> displayAvailableStock()
	{
		library.setConnection();
		return library.displayAvailableStock();
	}
	
	
	public ArrayList<String> showBorrowColumns()
	{
		library.setConnection();
		return library.showBorrowColumns();
	}
	
	public void borrowStockItem(int stockID)
	{
		library.setConnection();
		library.borrowStockItem(stockID);
	}
	
	public ArrayList<StockDisplay> displayBorrowedStock()
	{
		library.setConnection();
		return library.displayBorrowedStock();
	}
	
	public void returnStockItem(int stockID)
	{
		library.setConnection();
		library.returnStockItem(stockID);
	}
	
	
	
	/**public void updateDetails(String uname, String pword, String newFirstName, String newLastName, String newUserName, String newPassword)
	{
		m.updateDetails(uname, pword, newFirstName, newLastName, newUserName, newPassword);
	}
	
	public void deleteProfile(String uname, String pword)
	{
		m.deleteProfile(uname, pword);
	}**/
	
}//end of class
