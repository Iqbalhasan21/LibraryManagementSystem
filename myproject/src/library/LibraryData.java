package library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;

public class LibraryData {
    // Singleton instance
    private static LibraryData instance;
    
    private final ObservableList<BookItem> books = FXCollections.observableArrayList();
    private final ObservableList<Member> members = FXCollections.observableArrayList();  
    private final ObservableList<CheckedOutItem> checkedOutItems = FXCollections.observableArrayList();
    
    private int bookCount = 0;
    private int pubCount = 0;
    
    // Private constructor to prevent instantiation
    private LibraryData() {
        books.add(new Book("B001", "Intro to Algorithms", "Thomas H. Cormen", "Book", "Available", "MIT Press"));
        books.add(new Book("B002", "Clean Code", "Robert C. Martin", "Book", "Checked Out", "Prentice Hall"));
        books.add(new Publication("P001", "ML Advances", "Andrew Ng", "Publication", "Available", "ICML"));
        
        bookCount = 2; 
        pubCount = 1; 
        
        // Adding a checked Out item with user info
        checkedOutItems.add(new CheckedOutItem("B002", "Clean Code", LocalDate.now().plusDays(14), "Librarian"));
      
        // Demo student
        members.add(new Member("S101", "Demo Student", "demo@student.com", "01712345678", 0));
    }
    
    // Get singleton instance
    public static synchronized LibraryData getInstance() {
        if (instance == null) {
            instance = new LibraryData();
        }
        return instance;
    }
    
    // Get books list
    public ObservableList<BookItem> getBooks() {
        return books;
    }
    
    // Get members list
    public ObservableList<Member> getMembers() {
        return members;
    }
    
    // Get checked out items list
    public ObservableList<CheckedOutItem> getCheckedOutItems() {
        return checkedOutItems;
    }
    
    // Add a new book
    public void addBook(String title, String authors, String publisher, String category) {
        String id = String.format("B%03d", ++bookCount);
        books.add(new Book(id, title, authors, "Book", "Available", publisher));
    }
    
    // Add a new publication
    public void addPublication(String title, String authors, String journal, String category) {
        String id = String.format("P%03d", ++pubCount);
        books.add(new Publication(id, title, authors, "Publication", "Available", journal));
    }
    
    // Update book status
    public boolean updateBookStatus(String id, String newStatus) {
        for (BookItem book : books) {
            if (book.getId().equals(id)) {
                book.setStatus(newStatus);
                return true;
            }
        }
        return false;
    }
    
    // Find book by ID
    public BookItem findBookById(String id) {
        for (BookItem book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }
    
    // UPDATED: Add checked out item with user information
    public void addCheckedOutItem(String id, String title, LocalDate dueDate, String checkedOutBy) {
        // Check if already in the list
        for (CheckedOutItem item : checkedOutItems) {
            if (item.getId().equals(id)) {
                return; // Already in the list
            }
        }
        
        // Add new checked out item with user info
        checkedOutItems.add(new CheckedOutItem(id, title, dueDate, checkedOutBy));
    }
    
    // OLD method for backward compatibility (defaults to "Unknown")
    public void addCheckedOutItem(String id, String title, LocalDate dueDate) {
        addCheckedOutItem(id, title, dueDate, "Unknown");
    }
    
    // Find a member by ID
    public Member findMemberById(String id) {
        for (Member member : members) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }
    
    // Update member's checked out items count
    public void updateMemberCheckoutCount(String memberId, boolean isCheckout) {
        Member member = findMemberById(memberId);
        if (member != null) {
            int currentCount = member.getItemsCheckedOut();
            if (isCheckout) {
                member.setItemsCheckedOut(currentCount + 1);
            } else if (currentCount > 0) {
                member.setItemsCheckedOut(currentCount - 1);
            }
        }
    }
}