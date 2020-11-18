package libraryProject;

public class Book extends Stock
{

    private String author;
    private String publisher;
    private boolean isAvailable;
    private int memberID;
    private int numCopies = 0;
    private String year;

    public Book()
    {
    	//empty constructor
    }
    
    public Book(int libraryNum, String title, String author, String publisher,  boolean isAvailable, int memberID, int numCopies)
    {
    	super();
    	this.libraryNum = libraryNum;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isAvailable = isAvailable;
        this.memberID = memberID;
        this.numCopies = numCopies;
    }
    
  //this is what will be reflected in the user interface(scene)
    public Book(String title, String author, String publisher, String year)
    {
    	super();
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }

    /**
     * public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }
    **/

    public String getAuthor() 
    {
        return author;
    }

    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getPublisher() 
    {
        return publisher;
    }

    public void setPublisher(String publisher) 
    {
        this.publisher = publisher;
    }

    public boolean isAvailable() 
    {
        return isAvailable;
    }

    public void setAvailable(boolean available) 
    {
        isAvailable = available;
    }

    public int getMemberID() 
    {
        return memberID;
    }

    public void setMemberID(int memberID) 
    {
        this.memberID = memberID;
    }
    
    public void borrow(Member member)
	{
		
	}
	
	public void returnStock(Member member)
	{
		
	}
    
    

	@Override
	public String toString() 
	{
		return "Book [libraryNum=" + libraryNum + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", isAvailable=" + isAvailable + ", memberID=" + memberID + ", getLibraryNum()=" + getLibraryNum() + ", getTitle()="
				+ getTitle() + ", getAuthor()=" + getAuthor() + ", getPublisher()=" + getPublisher()
				+ ", isAvailable()=" + isAvailable() + ", getMemberID()=" + getMemberID() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
    
    
} // end of class