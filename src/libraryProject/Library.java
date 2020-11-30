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

	private ArrayList<Member>members;
	private ArrayList<Stock>stock;
	private ArrayList<MemberDisplay> memberDisplay;
		
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
		memberDisplay = new ArrayList<MemberDisplay>();
	}

	
	public Member getCurrentMember() 
	{
		return currentMember;
	}

	public void setCurrentMember(Member currentMember) 
	{
		this.currentMember = currentMember;
	}

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
	
	
	public boolean addBook(String title, String author, String publisher, String year)
	{
		return this.insertBook(title, author, publisher, year);
	}
	
	private boolean insertBook(String title, String author, String publisher, String year)
	{
		String sql = "INSERT INTO stock (title, author, publisher, year, type) VALUES (?, ?, ?, ?, 'book')";
		int bookID = 0;
		boolean wasSuccessful = false;
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
							bookID = rset.getInt(1);
							}
			
						}

			System.out.println("Book added to database");
			wasSuccessful = true;
			return wasSuccessful;
		}
		catch(SQLException e)
		{
			System.out.println("Error Executing Statement");
			e.printStackTrace();
			return wasSuccessful;
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
	public boolean addJournal(String title, String volume, String issue, String year)
	{
		return this.insertJournal(title, volume, issue, year);
	}
	
	private boolean insertJournal(String title, String volume, String issue, String year)
	{
		String sql = "INSERT INTO stock (title, volume, issue, year, type) VALUES (?, ?, ?, ?, 'journal')";
		int journalID = 0;
		boolean wasSuccessful = false;
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
							journalID = rset.getInt(1);
							}
			
						}

			System.out.println("Journal added to database");
			wasSuccessful = true;
			return wasSuccessful;
		}
		catch(SQLException e)
		{
			System.out.println("Error Executing Statement");
			e.printStackTrace();
			return wasSuccessful;
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
	
	public String checkUser(String email, String pword)
	{
		String userInDb = this.checkUserInDb(email, pword);
		return userInDb;
	}
	
		private String checkUserInDb(String email, String pword)
		{
			System.out.println(email + pword);
			String sql = "SELECT email, password, type FROM members WHERE email LIKE ? AND password LIKE ?";
//			int userID = 0;
			String userInDb = "";
			
			try 
			{
				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, email);
				pstmt.setString(2, pword);
				
				rset = pstmt.executeQuery();
				if(rset.next())
				{ 
					if(rset.getString("type").equals("user"))
					{
						System.out.println("User in DB. User is a member of the library");
						userInDb = "user";
						return userInDb;	
					}
					else if(rset.getString("type").equals("admin"))
					{
						System.out.println("User in DB. User is an admin of the library");
						userInDb = "admin";
						return userInDb;
					}
				}
				return userInDb;		
			}
			catch(SQLException e)
			{
				System.out.println("User Check Unsuccessful");
				e.printStackTrace();
				return userInDb;
			}
			
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
					//cols.add(rset.getString("Field"));
					String field = rset.getString("Field");
					if(!field.equals("type"))
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
		
		private ArrayList<StockDisplay> getAllStock()
		{

			String sql = "SELECT stockID, title, author, publisher, volume, issue, year, borrowerID FROM stock";

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
					if(!field.equals("borrowerID") && !field.equals("type"))
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
		
////get members
//		public ArrayList<Member> displayMembers()
//		{
//			return this.getMembers();
//		}
//		
//		
//		public ArrayList<String> showMemberColumns()
//		{
//			return this.getMemberColumns();
//		}
//		
//		private ArrayList<String> getMemberColumns()
//		{
//			String sql = "SHOW COLUMNS FROM members;";
//
//			ArrayList<String> cols = new ArrayList<>();
//			try 
//			{
//				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);				
//				rset = pstmt.executeQuery();
////				while (rset.next()) 
////				{
////					cols.add(rset.getString("Field"));
////				}
//				
//				while (rset.next()) 
//				{
//					String field = rset.getString("Field");
//					if(!field.equals("password"))
//					{
//							cols.add(field);
//					}
//				}
//
//				return cols;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("Cannot return columns from members");
//				e.printStackTrace();
//				
//				return null;
//			}
//		}
//		
//		private ArrayList<Member> getMembers()
//		{
//
//			String sql = "SELECT memberID, name, yearofbirth, street, town, postcode, email FROM members WHERE type = 'user'";
//
//			ArrayList<Member> members = new ArrayList<Member>();
//			try 
//			{
//				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);				
//				rset = pstmt.executeQuery();
//				while (rset.next()) 
//				{
//					Member m = new Member();
//					m.setId(rset.getInt("memberID"));
//					m.setName(rset.getString("name"));
//					m.setYearOfBirth(rset.getString("yearofbirth"));
//					m.setAddress(new Address(rset.getString("street"), rset.getString("town"), rset.getString("postcode")));
//					m.setEmail(rset.getString("email"));
//					//m.setPassword(rset.getString("password"));
//				
//		            members.add(m);
//				}
//				return members;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("Cannot return members");
//				e.printStackTrace();
//				
//				return null;
//			}
//		
//		}
//		
////search members
//		
//		public ArrayList<Member> searchMembers(String input)
//		{
//			return this.searchMembersEncap(input);
//		}
//		
//		private ArrayList<Member> searchMembersEncap(String input)
//		{
//			String sql = "SELECT * FROM members WHERE type = 'user' AND name LIKE '%"+input+"%' OR"
//					+ " yearofbirth LIKE '%"+input+"%' OR "
//					+ "street LIKE '%"+input+"%' OR "
//					+ "town LIKE '%"+input+"%' OR "
//					+ "postcode LIKE '%"+input+"%' OR "
//					+ "email LIKE '%"+input+"%' OR "
//					+ "memberID LIKE '%"+input+"%'";
//			
//			ArrayList<Member> searchMembers = new ArrayList<Member>();
//			try 
//			{
//				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//				rset = pstmt.executeQuery();
//				
//				while(rset.next())
//				{
//
//					Member m = new Member();
//					m.setId(rset.getInt("memberID"));
//					m.setName(rset.getString("name"));
//					m.setYearOfBirth(rset.getString("yearofbirth"));
//					m.setAddress(new Address(rset.getString("street"), rset.getString("town"), rset.getString("postcode")));
//					m.setEmail(rset.getString("email"));
//					//m.setPassword(rset.getString("password"));
//						
//					searchMembers.add(m);
//				}
//				return searchMembers;
//						
//			}
//			catch(SQLException e)
//			{
//				System.out.println("Cannot return members");
//				e.printStackTrace();
//				return null;
//			}
//		}
	//////////new version
		
		//get members
				public ArrayList<MemberDisplay> displayMembers()
				{
					return this.getMembers();
				}
				
				
				public ArrayList<String> showMemberColumns()
				{
					return this.getMemberColumns();
				}
				
				private ArrayList<String> getMemberColumns()
				{
					String sql = "SHOW COLUMNS FROM members;";

					ArrayList<String> cols = new ArrayList<>();
					try 
					{
						pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);				
						rset = pstmt.executeQuery();
//						
						
						while (rset.next()) 
						{
							String field = rset.getString("Field");
							if(!field.equals("type") && !field.equals("password"))
							{
									cols.add(field);
							}
						}

						return cols;
					}
					catch(SQLException e)
					{
						System.out.println("Cannot return columns from members");
						e.printStackTrace();
						
						return null;
					}
				}
				
				private ArrayList<MemberDisplay> getMembers()
				{

					String sql = "SELECT memberID, name, yearofbirth, street, town, postcode, email FROM members WHERE type = 'user'";

					ArrayList<MemberDisplay> memberDisplay = new ArrayList<MemberDisplay>();
					try 
					{
						pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);				
						rset = pstmt.executeQuery();
						while (rset.next()) 
						{
							MemberDisplay md = new MemberDisplay();
							md.setMemberID(rset.getInt("memberID"));
							md.setName(rset.getString("name"));
							md.setYearofbirth(rset.getString("yearofbirth"));
							md.setStreet(rset.getString("street"));
							md.setTown(rset.getString("town"));
							md.setPostcode(rset.getString("postcode"));
							md.setEmail(rset.getString("email"));
							//m.setPassword(rset.getString("password"));
						
				            memberDisplay.add(md);
				            
						}
						return memberDisplay;
					}
					catch(SQLException e)
					{
						System.out.println("Cannot return members");
						e.printStackTrace();
						
						return null;
					}
				
				}
				
		//search members
				
				public ArrayList<MemberDisplay> searchMembers(String input)
				{
					return this.searchMembersEncap(input);
				}
				
				private ArrayList<MemberDisplay> searchMembersEncap(String input)
				{
					String sql = "SELECT * FROM members WHERE type = 'user' AND name LIKE '%"+input+"%' OR"
							+ " yearofbirth LIKE '%"+input+"%' OR "
							+ "street LIKE '%"+input+"%' OR "
							+ "town LIKE '%"+input+"%' OR "
							+ "postcode LIKE '%"+input+"%' OR "
							+ "email LIKE '%"+input+"%' OR "
							+ "memberID LIKE '%"+input+"%'";
					
					ArrayList<MemberDisplay> searchMembers = new ArrayList<MemberDisplay>();
					try 
					{
						pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						rset = pstmt.executeQuery();
						
						while(rset.next())
						{
							MemberDisplay md = new MemberDisplay();
							md.setMemberID(rset.getInt("memberID"));
							md.setName(rset.getString("name"));
							md.setYearofbirth(rset.getString("yearofbirth"));
							md.setStreet(rset.getString("street"));
							md.setTown(rset.getString("town"));
							md.setPostcode(rset.getString("postcode"));
							md.setEmail(rset.getString("email"));
							//m.setPassword(rset.getString("password"));
								
							searchMembers.add(md);
						}
						return searchMembers;
								
					}
					catch(SQLException e)
					{
						System.out.println("Cannot return members");
						e.printStackTrace();
						return null;
					}
				}	
		
		
} // end of class
