package miniproject;

public interface LibraryService {

	public void addBook(Book book) throws InValidBookException;
	
	public void issueBook(int bookId) throws InValidBookException;
	
	public void returnBook(int bookId) throws InValidBookException;
	
	public void registerUser(User user) throws InValidUserException;
	
	
}
