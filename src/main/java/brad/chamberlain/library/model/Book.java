package brad.chamberlain.library.model;

import javax.persistence.*;

/**
 * There are a few things that a real "book" would have to look different.  I was just going for simple.
 * In reality, a book would most likely have a many-to-one relationship with authors.  And a large library would most
 * likely be a many-to-many.
 *
 * I also didn't implement any type of checkout for the book.  That code would probably not go here anyway.  I would
 * start by adding a couple of columns, Date checkedOut and Number dueInDays.  The business layer would have a
 * boolean method for checked out that would look at the checkout date to see if it was checked out.  The due in days
 * could be incremented if an extention was granted.
 */
@Entity
@Table(name = "books")
public class Book
{
    private long id;
    private String title;
    private String author;
    private int pages;

    public Book()
    {

    }

    public Book(String title, String author, int pages)
    {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    @Column(name = "author", nullable = false)
    public String getAuthor()
    {
        return author;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }

    @Column(name = "pages", nullable = false)
    public int getPages()
    {
        return this.pages;
    }
    public void setPages(int pages)
    {
        this.pages = pages;
    }
}
