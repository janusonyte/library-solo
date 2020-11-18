package libraryProject;

public class Journal extends Stock
{
	private int volume;
	private boolean isAvailable;
	private int memberID;
	private int numCopies = 0;
	private int issue;
	private String year;
	
	public Journal()
	{
		//empty constructor
	}
	
	public Journal(int libraryNum, String title, int volume, int issue, boolean isAvailable, int memberID, int numCopies)
	{
		super();
		this.libraryNum = libraryNum;
		this.title = title;
		this.volume = volume;
		this.isAvailable = isAvailable;
        this.memberID = memberID;
        this.numCopies = numCopies;
        this.issue = issue;
	}
	
	//this is what will be reflected in the user interface(scene)
	public Journal(String title, int volume, int issue, String year)
	{
		super();
		this.title = title;
		this.volume = volume;
        this.issue = issue;
        this.year = year;
	}
	
	
	public void borrow(Member member)
	{
		
	}
	
	public void returnStock(Member member)
	{
		
	}
	
} // end of class
