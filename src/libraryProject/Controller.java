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
	
	
	public boolean sanitiseInput(String name, String email, String pw, String pc)

	{
		System.out.println(name + email + pw + pc);
		
		String regexName = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$"; //name can have spaces
		String regexEmail = "^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String regexPassword = "^(?=.*\\d).{4,8}$"; //special character required
		String regexPostcode = "([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\s?[0-9][A-Za-z]{2})";
		//String regexPostcode = "^(GIR 0AA)|[a-z-[qvx]](?:\\d|\\d{2}|[a-z-[qvx]]\\d|[a-z-[qvx]]\\d[a-z-[qvx]]|[a-z-[qvx]]\\d{2})(?:\\s?\\d[a-z-[qvx]]{2})?$";
		boolean a = Pattern.matches(regexName, name);
		boolean b = Pattern.matches(regexEmail, email);
		boolean c = Pattern.matches(regexPassword, pw);
		boolean d = Pattern.matches(regexPostcode, pc);
		//boolean f = Pattern.matches(regexName, town);
		//boolean b = Pattern.matches(regex, lname);
		
		
		if(a && b && c && d  == true)
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
	
	public String checkUser(String email, String pword)
	{
		library.setConnection();
		String userInDb = library.checkUser(email, pword);
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
	
	
//stock	
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
	
//members
	
//	public ArrayList<Member> displayMembers()
//	{
//		library.setConnection();
//		return library.displayMembers();
//	}
//	
//	public ArrayList<String> getMemberColumns()
//	{
//		library.setConnection();
//		return library.showMemberColumns();
//	}
//	
//	public ArrayList<Member> searchMembers(String input)
//	{
//		library.setConnection();
//		return library.searchMembers(input);
//	}
	
	//new version
	
	public ArrayList<MemberDisplay> displayMembers()
	{
		library.setConnection();
		return library.displayMembers();
	}
	
	public ArrayList<String> getMemberColumns()
	{
		library.setConnection();
		return library.showMemberColumns();
	}
	
	public ArrayList<MemberDisplay> searchMembers(String input)
	{
		library.setConnection();
		return library.searchMembers(input);
	}
	
	

	
}//end of class
