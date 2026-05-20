package day1java;

public class Book {

	private int id ;
	
	private String author;
	
	private String title;
	
	private boolean issued;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isIssued() {
		return issued;
	}

	public void setIssued(boolean issued) {
		this.issued = issued;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", title=" + title + ", issued=" + issued + "]";
	}

	public Book(int id, String author, String title, boolean issued) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.issued = issued;
	}
	
	public Book() {
		
	}
}
