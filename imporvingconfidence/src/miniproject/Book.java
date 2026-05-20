package miniproject;

public class Book {

	private int id;
	
	private String author;
	
	private boolean issued;
	
	private String title;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isIssued() {
		return issued;
	}

	public void setIssued(boolean issued) {
		this.issued = issued;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", issued=" + issued + ", title=" + title + "]";
	}

	public Book(int id, String author, boolean issued, String title) {
		super();
		this.id = id;
		this.author = author;
		this.issued = issued;
		this.title = title;
	}
	
	public Book() {
		
	}
	
}
