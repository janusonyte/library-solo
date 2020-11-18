package libraryProject;

public class Address 
{

	String street;
	String town;
	String postcode;
	
	public Address(String street, String town, String postcode)
	{
		this.street = street;
		this.town = town;
		this.postcode = postcode;
	}
	
	public String getStreet() 
	{
		return street;
	}
	
	public void setStreet(String street) 
	{
		this.street = street;
	}
	
	public String getTown() 
	{
		return town;
	}
	
	public void setTown(String town) 
	{
		this.town = town;
	}
	
	public String getPostcode() 
	{
		return postcode;
	}
	
	public void setPostcode(String postcode) 
	{
		this.postcode = postcode;
	}
	
	
	
	
	
} // end of class
