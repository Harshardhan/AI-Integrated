package miniproject;


public class LibraryTest {

    public static void main(String[] args) {

        LibraryService libraryService = new LibraryServiceImpl();

        try {
            // --------------------
            // Register Users
            // --------------------
            libraryService.registerUser(
                new User(101, "Harsha", "9999999999", "Hyderabad")
            );

            libraryService.registerUser(
                new User(102, "Vardhan", "8888888888", "Bangalore")
            );

            // --------------------
            // Add Books
            // --------------------
            libraryService.addBook(
                new Book(1, "Robert Martin", false, "Clean Code")
            );

            libraryService.addBook(
                new Book(2, "Joshua Bloch", false, "Effective Java")
            );

            libraryService.addBook(
                new Book(3, "Martin Fowler", false, "Refactoring")
            );

            // --------------------
            // Issue Book
            // --------------------
            libraryService.issueBook(1);

            // ❌ Try issuing same book again (should fail)
            libraryService.issueBook(1);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            // --------------------
            // Return Book
            // --------------------
            libraryService.returnBook(1);

            // ❌ Try returning same book again (should fail)
            libraryService.returnBook(1);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            // ❌ Issue non-existing book
            libraryService.issueBook(99);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
