package libraryProject;

public class MemberDisplay 

{
	private int memberID;
	private String name;
	private String yearofbirth;
	private String street;
	private String town;
	private String postcode;
	private String email;
	
	
	public MemberDisplay()
	{
		
	}
	
	
	public MemberDisplay(int memberID, String name, String yearofbirth, String street, String town, String postcode,
			String email) {
		super();
		this.memberID = memberID;
		this.name = name;
		this.yearofbirth = yearofbirth;
		this.street = street;
		this.town = town;
		this.postcode = postcode;
		this.email = email;
	}
	
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYearofbirth() {
		return yearofbirth;
	}
	public void setYearofbirth(String yearofbirth) {
		this.yearofbirth = yearofbirth;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
}//end of class
