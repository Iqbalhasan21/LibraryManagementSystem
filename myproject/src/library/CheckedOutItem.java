package library;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckedOutItem {
    private final StringProperty id;
    private final StringProperty title;
    private final StringProperty dueDate;
    private final StringProperty checkedOutBy;  // NEW: User who checked out
    private final LocalDate dueDateValue;

    // Updated constructor with user information
    public CheckedOutItem(String id, String title, LocalDate dueDate, String checkedOutBy) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.dueDateValue = dueDate;
        this.dueDate = new SimpleStringProperty(
            dueDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
        );
        this.checkedOutBy = new SimpleStringProperty(checkedOutBy);
    }
    
    // Old constructor for backward compatibility (defaults to "Unknown")
    public CheckedOutItem(String id, String title, LocalDate dueDate) {
        this(id, title, dueDate, "Unknown");
    }

    public String getId() { return id.get();}
    public String getTitle() {return title.get();}
    public String getDueDate() {return dueDate.get();}
    public String getCheckedOutBy() {return checkedOutBy.get();}  // NEW

    public StringProperty idProperty() {return id;}
    public StringProperty titleProperty() { return title;}
    public StringProperty dueDateProperty() {return dueDate;}
    public StringProperty checkedOutByProperty() {return checkedOutBy;}  // NEW
    public LocalDate getDueDateValue() {return dueDateValue;}
}