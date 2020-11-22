package libraryProject;

public class Person 
{

	private String yearOfBirth;
	private String name;
	private Address address;
	
	public Person()
	{
		//empty constructor
	}
	
	public Person(String yearOfBirth, String name, Address address) 
	{
		
		this.yearOfBirth = yearOfBirth;
		this.name = name;
		this.address = address;
	}

	public String getYearOfBirth() 
	{
		return yearOfBirth;
	}

	public void setYearOfBirth(String yearOfBirth) 
	{
		this.yearOfBirth = yearOfBirth;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Address getAddress() 
	{
		return address;
	}

	public void setAddress(Address address) 
	{
		this.address = address;
	}

	@Override
	public String toString() 
	{
		return "Person [yearOfBirth=" + yearOfBirth + ", name=" + name + ", address=" + address + ", getYearOfBirth()="
				+ getYearOfBirth() + ", getName()=" + getName() + ", getAddress()=" + getAddress() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
} // end of class
