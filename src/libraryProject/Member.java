package libraryProject;

public class Member extends Person

{
    private int id;
    private String password;
    private String email;
    //private String yearOfBirth;
    
    
    
    public Member() 
    {
    	
    	//empty constructor
    }
    
    public Member(int id, String yearOfBirth, String name, Address address, String password, String email)
    {
    	super(); //the Member class is now a subclass (extends Person)
    	this.id = id;
    	this.password = password;
    	this.email = email;
    	
    	//this.numberofbooks = numberofbooks;
    	
    }
    
    public Member(String name, String yearOfBirth, String email, String password, String street, String town, String postcode)
    {
    	super(); //the Member class is now a subclass (extends Person)
    	this.setYearOfBirth(yearOfBirth);
    	this.setName(name);
    	this.password = password;
    	this.email = email; 
    	this.setAddress(new Address(street, town, postcode));
    }
    
    public Member(int id, String name, String yearOfBirth, String street, String town, String postcode, String email, String password)
    {
    	super(); //the Member class is now a subclass (extends Person)
    	this.setYearOfBirth(yearOfBirth);
    	this.setName(name);
    	this.id = id;
    	this.password = password;
    	this.email = email;
    	this.setAddress(new Address(street, town, postcode));

    }

	public String getPassword() 
	{
		return password;
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

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	
} // end of class
