package miniproject;

import java.util.ArrayList;
import java.util.List;

public class LibraryServiceImpl implements LibraryService{

	private List<Book> books = new ArrayList<>();
	
	private List<User> users = new ArrayList<>();
	
	@Override
	public void addBook(Book book) throws InValidBookException {

		validateBookDetails(book);
		
		if(findBookById(book.getId()) != null) {
			throw new InValidBookException("Book already exists..."+book.getId());
		}
		
		books.add(book);
		
        System.out.println("Book added successfully");

		
	}
	private Book findBookById(int id) {

		for(Book book : books) {
			if(book.getId() == id) {
				return book;
			}
		}
		return null;
	}
	private void validateBookDetails(Book book) throws InValidBookException {
        if (book == null || book.getTitle() == null || book.getAuthor() == null) {
			throw new InValidBookException("InValid Book Details...");
		}
		
	}

	@Override
	public void issueBook(int bookId) throws InValidBookException {

		Book book = findBookById(bookId);
		
		if(book == null) {
			throw new InValidBookException(" Book details are not found...");
		}
		
		if(book.isIssued()) {
			throw new InValidBookException("Book already issued.");
		}
		
		book.setIssued(true);
		System.out.println("Book issued successfully");
	}

	@Override
	public void returnBook(int bookId) throws InValidBookException {
		
		Book returnBook = findBookById(bookId);
		
		if(returnBook == null) {
			throw new InValidBookException(" Book details are not found...");
		}
		
		if(!returnBook.isIssued()) {
			throw new InValidBookException("Book was not issued...");
		}
		
		returnBook.setIssued(false);
		System.out.println("Books are returned successfully...");
		
	}
	@Override
	public void registerUser(User user) throws InValidUserException {
		
		if(user == null || user.getUserName() == null) {
			throw new InValidUserException("InValid user...");
		}
		
		users.add(user);
		System.out.println("User will be registered Successfully... ");
	}

	
}
