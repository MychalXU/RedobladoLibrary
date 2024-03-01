import java.util.ArrayList;
import java.util.List;

interface LibraryItem {
    void borrow();

    void returnItem();

    boolean isBorrowed();

    String getDescription();
}

class Book implements LibraryItem {
    private boolean borrowed;
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public void borrow() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String getDescription() {
        return "Book: " + title + " by " + author;
    }
}

class DVD implements LibraryItem {
    private boolean borrowed;
    private String title;
    private String director;

    public DVD(String title, String director) {
        this.title = title;
        this.director = director;
    }

    @Override
    public void borrow() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String getDescription() {
        return "DVD: " + title + " directed by " + director;
    }
}

abstract class LibraryUser {
    protected List<LibraryItem> borrowedItems = new ArrayList<>();
    protected String name;

    public LibraryUser(String name) {
        this.name = name;
    }

    public void borrowItem(LibraryItem item) {
        if (!item.isBorrowed()) {
            item.borrow();
            borrowedItems.add(item);
        }
    }

    public void returnItem(LibraryItem item) {
        if (borrowedItems.contains(item)) {
            item.returnItem();
            borrowedItems.remove(item);
        }
    }

    public void printItemsBorrowed() {
        System.out.println(this.getClass().getSimpleName() + ": " + name);
        System.out.println("Borrowed Items:");
        for (LibraryItem item : borrowedItems) {
            System.out.println("- " + item.getDescription());
        }
    }
}

class Student extends LibraryUser {
    public Student(String name) {
        super(name);
    }
}

class Teacher extends LibraryUser {
    public Teacher(String name) {
        super(name);
    }
}

public class makai {
    public static void main(String[] args) {
        Book book1 = new Book("To Kill a Mockingbird", "Harper Lee");
        DVD dvd1 = new DVD("The Shawshank Redemption", "Frank Darabont");

        Student student = new Student("Alice");
        Teacher teacher = new Teacher("Bob");

        student.borrowItem(book1);
        teacher.borrowItem(dvd1);

        student.printItemsBorrowed();
        teacher.printItemsBorrowed();

        student.returnItem(book1);
        teacher.returnItem(dvd1);

        student.printItemsBorrowed();
        teacher.printItemsBorrowed();
    }
}