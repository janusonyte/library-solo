package libraryProject;

public class Member extends Person

{
    private int id;
    private String password;
    private String email;
    private int numberofbooks;
    private String yearOfBirth;
    
    
    
    public Member() 
    {
    	
    	//empty constructor
    }
    
    public Member(int id, String yearOfBirth, String name, Address address, String password, String email, int numberofbooks)
    {
    	super(); //the Member class is now a subclass (extends Person)
    	this.id = id;
    	this.password = password;
    	this.email = email;
    	this.numberofbooks = numberofbooks;
    	
    }
    
    public Member(String name, String yearOfBirth, String email, String password, String street, String town, String postcode)
    {
    	super(); //the Member class is now a subclass (extends Person)
    	this.yearOfBirth = yearOfBirth;
    	this.password = password;
    	this.email = email; 
    	this.getAddress().postcode = postcode;
    	this.getAddress().street = street;
    	this.getAddress().town = town;
    }
    
    
	
//	public int getId() 
//	{
//		return id;
//	}


	public String getPassword() 
	{
		return password;
	}


	public String getName(Person person) 
	{
		return person.getName();
	}


	public String getEmail() 
	{
		return email;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

//	public String getYearOfBirth() 
//	{
//		return yearOfBirth;
//	}
//
//	public void setYearOfBirth(String yearOfBirth) 
//	{
//		this.yearOfBirth = yearOfBirth;
//	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	


//	public int getNumberofbooks() 
//	{
//		return numberofbooks;
//	}
//	
	
//
//	void newMember(String strn,String strst, String strtown, String strpc, int yr, int mid) 
//	{
//		
//	}
	
} // end of class
