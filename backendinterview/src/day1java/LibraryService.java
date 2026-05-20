package day1java;

public interface LibraryService {

	public void addBook(Book book);
	
	public void returnBook(int  bookId);
	
	public void issueBook(int bookId);
	
}
