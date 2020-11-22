package libraryProject;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//for 'model'
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class Library 
{
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rset;
//current user
	
	Member currentMember;
	
	

	private List<Member>members;
	private ArrayList<Stock>stock;
		
	// Singleton object --similar to the Bank project
	private static Library library;

	public static Library getInstance()
	{   
		if (library == null)
		{
			library = new Library();
		}
		return library; 
	} 
	
	public Library() 
	{
		members = new ArrayList<Member>();//instantiating members
		stock = new ArrayList<Stock>();//instantiating stock
	}

	
	public Member getCurrentMember() 
	{
		return currentMember;
	}

	public void setCurrentMember(Member currentMember) 
	{
		this.currentMember = currentMember;
	}

	public List<Member> getMembers() 
	{
		return members;
	}
	
//	public void addBook(String title, String author, String publisher, boolean isAvailable, int memberID, int numCopies) 
//	{
//		int libraryNum = stock.size()+ 1;
//		isAvailable = true;
//		stock.add(new Book(libraryNum, title, author, publisher, isAvailable, memberID, numCopies));
//	}
	
//	public void addJournal(String title, int volume, int issue, boolean isAvailable, int memberID, int numCopies) 
//	{
//		int libraryNum = stock.size() +1;
//		isAvailable = true;
//		stock.add(new Journal(libraryNum, title, volume, issue, isAvailable, memberID, numCopies));
//	}

//	public List<Stock> getStock() 
//	{
//		return stock;
//	}
	
	public String getBooks() 
	{
		return null;
	}
	
	public String getJournals() 
	{
		return null;
	}
	
	
	
	
	///beginning the 'model'
	
	public void setConnection()
	{
		this.connectdb();
	}
	
	
	private void connectdb()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useTimezone=true&serverTimezone=UTC", "root", "root");
			System.out.println("Connection successful");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Connection Unsuccessful: Class Not Found");
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			System.out.println("Connection Unsuccessful: SQL");
			e.printStackTrace();
		}
	}
	
	public void setUser(String name, int yob, String email, String pw, String street, String town, String pc)
	{
		this.insertUser(name, yob, email, pw, street, town, pc);
	}
	
	private void insertUser(String name, int yob, String email, String pw, String street, String town, String pc)
	{
		String sql = "INSERT INTO members (name, yearofbirth, email, password, street, town, postcode, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		//final value is TYPE (hardcoded)
		int userID = 0;

		try 
		{
						pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						pstmt.setString (1, name);
						pstmt.setInt (2, yob);
						pstmt.setString  (3, email);
						pstmt.setString(4, pw);
						pstmt.setString(5, street);
						pstmt.setString(6, town);
						pstmt.setString(7, pc);
						pstmt.setString(8, "user");
						
						int rowAffected = pstmt.executeUpdate();
						if(rowAffected == 1)
						{
							rset = pstmt.getGeneratedKeys();
							if(rset.next())
							{
							userID = rset.getInt(1);
							}
			
						}

			System.out.println("User added to database");
		}
		catch(SQLException e)
		{
			System.out.println("Error Executing Statement");
			e.printStackTrace();
		}
				finally
				{
					try 
					{
						if(rset != null)
						{
							rset.close();
						}
					}
					catch(SQLException e)
					{
						System.out.println("Unable to close Result Set");
						e.printStackTrace();
					}
				}

	}
	
	
	public void addBook(String title, String author, String publisher, String year)
	{
		this.insertBook(title, author, publisher, year);
	}
	
	private void insertBook(String title, String author, String publisher, String year)
	{
		String sql = "INSERT INTO stock (title, author, publisher, year) VALUES (?, ?, ?, ?)";
		int userID = 0;

		try 
		{
						pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						pstmt.setString (1, title);
						pstmt.setString (2, author);
						pstmt.setString  (3, publisher);
						pstmt.setString(4, year);
						
						int rowAffected = pstmt.executeUpdate();
						if(rowAffected == 1)
						{
							rset = pstmt.getGeneratedKeys();
							if(rset.next())
							{
							userID = rset.getInt(1);
							}
			
						}

			System.out.println("Book added to database");
		}
		catch(SQLException e)
		{
			System.out.println("Error Executing Statement");
			e.printStackTrace();
		}
				finally
				{
					try 
					{
						if(rset != null)
						{
							rset.close();
						}
					}
					catch(SQLException e)
					{
						System.out.println("Unable to close Result Set");
						e.printStackTrace();
					}
				}

	}
	
//add journal to database	
	public void addJournal(String title, String volume, String issue, String year)
	{
		this.insertJournal(title, volume, issue, year);
	}
	
	private void insertJournal(String title, String volume, String issue, String year)
	{
		String sql = "INSERT INTO stock (title, volume, issue, year) VALUES (?, ?, ?, ?)";
		int userID = 0;

		try 
		{
						pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						pstmt.setString (1, title);
						pstmt.setString (2, volume);
						pstmt.setString  (3, issue);
						pstmt.setString(4, year);
						
						int rowAffected = pstmt.executeUpdate();
						if(rowAffected == 1)
						{
							rset = pstmt.getGeneratedKeys();
							if(rset.next())
							{
							userID = rset.getInt(1);
							}
			
						}

			System.out.println("Journal added to database");
		}
		catch(SQLException e)
		{
			System.out.println("Error Executing Statement");
			e.printStackTrace();
		}
				finally
				{
					try 
					{
						if(rset != null)
						{
							rset.close();
						}
					}
					catch(SQLException e)
					{
						System.out.println("Unable to close Result Set");
						e.printStackTrace();
					}
				}

	}
	
	
	//check if user is in database
	
	public boolean checkUser(String email, String pword)
	{
		boolean userInDb = this.checkUserInDb(email, pword);
		return userInDb;
	}
	
		private boolean checkUserInDb(String email, String pword)
		{
			System.out.println(email + pword);
			String sql = "SELECT email, password FROM members WHERE email LIKE ? AND password LIKE ?";
//			int userID = 0;
			boolean userInDb = false;
			
			try 
			{
				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, email);
				pstmt.setString(2, pword);
				
				rset = pstmt.executeQuery();
				if(rset.next())
				{
//					userID = rset.getInt(1);
					System.out.println("User in DB");
					userInDb = true;
					return userInDb;
				}
				System.out.println("User not in DB");
				
				return false;

						
			}
			catch(SQLException e)
			{
				System.out.println("User Check Unsuccessful");
				e.printStackTrace();
				
			}
			return userInDb;
		}
		
//get user info
		public Member getUser(String email, String pword)
		{
			return this.getUserInfo(email, pword);
		}
			
		
		private Member getUserInfo(String email, String pword)
		{	
			String sql = "SELECT memberID, name, yearofbirth, email, password, street, town, postcode FROM members WHERE email LIKE ? AND password LIKE ?";
			
			try 
			{
				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, email);
				pstmt.setString(2, pword);
				
				rset = pstmt.executeQuery();
				if(rset.next())
				{
					Member member = new Member();
					member.setId(rset.getInt("memberID"));
					member.setName(rset.getString("name"));
					member.setYearOfBirth(rset.getString("yearofbirth"));
					member.setEmail(rset.getString("email"));
					member.setPassword(rset.getString("password"));
					member.setAddress(new Address(rset.getString("street"), rset.getString("town"), rset.getString("postcode")));
					
					currentMember = member;
					
					return currentMember;
					
////					userID = rset.getInt(1);
//					System.out.println("User returned successfully");
//					int ID = rset.getInt("memberID");
//					String name = rset.getString("name");
////					int yob = rset.getInt("yearofbirth");
//					String yob = Integer.toString(rset.getInt("yearofbirth"));
//					String email2 = rset.getString("email");
//					String password = rset.getString("password");
//					String street = rset.getString("street");
//					String town = rset.getString("town");
//					String postcode = rset.getString("postcode");
					
//					ret.add(name);
//					ret.add(yob);
//					ret.add(email);
//					ret.add(password);
//					ret.add(street);
//					ret.add(town);
//					ret.add(postcode);
//					return ret;
				}
				return null;
						
			}
			catch(SQLException e)
			{
				System.out.println("Cannot return user");
				e.printStackTrace();
				return null;
			}
			
		}
		
		
		

////get stock
		
		
		public ArrayList<StockDisplay> displayStock()
		{
			return this.getAllStock();
		}
		
		
		public ArrayList<String> showColumns()
		{
			return this.getColumns();
		}
		
		private ArrayList<String> getColumns()
		{
			String sql = "SHOW COLUMNS FROM stock;";

			ArrayList<String> cols = new ArrayList<>();
			try 
			{
				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);				
				rset = pstmt.executeQuery();
				while (rset.next()) 
				{
					cols.add(rset.getString("Field"));
				}

				return cols;
			}
			catch(SQLException e)
			{
				System.out.println("Cannot return column");
				e.printStackTrace();
				
				return null;
			}
		}
		
		private ArrayList<StockDisplay> getAllStock()
		{

			String sql = "SELECT * FROM stock";

			ArrayList<StockDisplay> stock = new ArrayList<StockDisplay>();
			try 
			{
				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);				
				rset = pstmt.executeQuery();
				while (rset.next()) 
				{
					StockDisplay s = new StockDisplay();
					s.setStockID(rset.getInt("stockID"));
					s.setTitle(rset.getString("title"));
					s.setAuthor(rset.getString("author"));
					s.setPublisher(rset.getString("publisher"));
					s.setVolume(rset.getString("volume"));
					s.setIssue(rset.getString("issue"));
					s.setYear(rset.getString("year"));
					s.setBorrowerID(rset.getInt("borrowerID"));

		            stock.add(s);
				}
				return stock;
			}
			catch(SQLException e)
			{
				System.out.println("Cannot return stock");
				e.printStackTrace();
				
				return null;
			}
		
		}
///search stock
		
		public ArrayList<StockDisplay> searchStock(String input)
		{
			return this.searchStockEncap(input);
		}
		
		private ArrayList<StockDisplay> searchStockEncap(String input)
		{
//			String sql = "SELECT * FROM stock WHERE title OR author OR publisher OR volume OR issue OR year LIKE ?";
			String sql = "SELECT * FROM stock WHERE title LIKE '%"+input+"%' OR"
					+ " author LIKE '%"+input+"%' OR "
					+ "publisher LIKE '%"+input+"%' OR "
					+ "volume LIKE '%"+input+"%' OR "
					+ "issue LIKE '%"+input+"%' OR "
					+ "year LIKE '%"+input+"%'";
			ArrayList<StockDisplay> searchStock = new ArrayList<StockDisplay>();
			try 
			{
				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				rset = pstmt.executeQuery();
				
				while(rset.next())
				{
					StockDisplay s = new StockDisplay();
					s.setStockID(rset.getInt("stockID"));
					s.setTitle(rset.getString("title"));
					s.setAuthor(rset.getString("author"));
					s.setPublisher(rset.getString("publisher"));
					s.setVolume(rset.getString("volume"));
					s.setIssue(rset.getString("issue"));
					s.setYear(rset.getString("year"));
					s.setBorrowerID(rset.getInt("borrowerID"));
					
					searchStock.add(s);
				}
				return searchStock;
						
			}
			catch(SQLException e)
			{
				System.out.println("Cannot return stock items");
				e.printStackTrace();
				return null;
			}
		}
		
//get all available stock for borrowing
		
		public ArrayList<StockDisplay> displayAvailableStock()
		{
			return this.getAvailableStock();
		}
		
		private ArrayList<StockDisplay> getAvailableStock()
		{

			String sql = "SELECT stockID, title, author, publisher, volume, issue, year FROM stock WHERE borrowerID IS NULL";

			ArrayList<StockDisplay> stock = new ArrayList<StockDisplay>();
			try 
			{
				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);				
				rset = pstmt.executeQuery();
				while (rset.next()) 
				{
					StockDisplay s = new StockDisplay();
					s.setStockID(rset.getInt("stockID"));
					s.setTitle(rset.getString("title"));
					s.setAuthor(rset.getString("author"));
					s.setPublisher(rset.getString("publisher"));
					s.setVolume(rset.getString("volume"));
					s.setIssue(rset.getString("issue"));
					s.setYear(rset.getString("year"));
					
		            stock.add(s);
				}
				return stock;
			}
			catch(SQLException e)
			{
				System.out.println("Cannot return available stock");
				e.printStackTrace();
				
				return null;
			}
		
		}
		
//show columns for borrowing		
		public ArrayList<String> showBorrowColumns()
		{
			return this.getBorrowColumns();
		}
		
		private ArrayList<String> getBorrowColumns()
		{
			String sql = "SHOW COLUMNS FROM stock;";

			ArrayList<String> cols = new ArrayList<>();
			try 
			{
				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);				
				rset = pstmt.executeQuery();
				while (rset.next()) 
				{
					String field = rset.getString("Field");
					if(!field.equals("borrowerID"))
					{
							cols.add(field);
					}
				}

				return cols;
			}
			catch(SQLException e)
			{
				System.out.println("Cannot return column");
				e.printStackTrace();
				
				return null;
			}
		}
///borrow stock item
		
		public void borrowStockItem(int stockID)
		{
			this.borrowStockItemEncap(stockID);
		}
		
		private void borrowStockItemEncap(int stockID)
		{
			String sql = "SELECT * FROM stock WHERE stockID LIKE ?";
			try 
			{
				int memberID = currentMember.getId();

				String sqlUpdate = "UPDATE stock SET borrowerID = ? WHERE stockID = ?";

				pstmt = con.prepareStatement(sqlUpdate);
				
				pstmt.setInt(1, memberID);
				pstmt.setInt(2, stockID);
				pstmt.executeUpdate();

			}
			catch(SQLException e)
			{
				System.out.println("Cannot borrow book");
				e.printStackTrace();
			}
		}
		
///display borrowed stock
		
		public ArrayList<StockDisplay> displayBorrowedStock()
		{
			return this.getBorrowedStock();
		}
		
		private ArrayList<StockDisplay> getBorrowedStock()
		{

			String sql = "SELECT stockID, title, author, publisher, volume, issue, year FROM stock WHERE borrowerID IS NOT NULL";

			ArrayList<StockDisplay> stock = new ArrayList<StockDisplay>();
			try 
			{
				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);				
				rset = pstmt.executeQuery();
				while (rset.next()) 
				{
					StockDisplay s = new StockDisplay();
					s.setStockID(rset.getInt("stockID"));
					s.setTitle(rset.getString("title"));
					s.setAuthor(rset.getString("author"));
					s.setPublisher(rset.getString("publisher"));
					s.setVolume(rset.getString("volume"));
					s.setIssue(rset.getString("issue"));
					s.setYear(rset.getString("year"));
					
		            stock.add(s);
				}
				return stock;
			}
			catch(SQLException e)
			{
				System.out.println("Cannot return borrowed stock");
				e.printStackTrace();
				
				return null;
			}
		
		}
		
//return stock item
		public void returnStockItem(int stockID)
		{
			this.returnStockItemEncap(stockID);
		}
		
		private void returnStockItemEncap(int stockID)
		{
			//String sql = "SELECT * FROM stock WHERE stockID LIKE ?";
			
			try 
			{
				//int memberID = currentMember.getId();

				String sqlUpdate = "UPDATE stock SET borrowerID = NULL WHERE stockID = ?";

				pstmt = con.prepareStatement(sqlUpdate);
				
				//pstmt.setInt(1, memberID);
				pstmt.setInt(1, stockID);
				pstmt.executeUpdate();

			}
			catch(SQLException e)
			{
				System.out.println("Cannot return book");
				e.printStackTrace();
			}
		}
		
		
		
} // end of class
