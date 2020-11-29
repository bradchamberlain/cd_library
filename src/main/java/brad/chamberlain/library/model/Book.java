package brad.chamberlain.library.model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
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
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author", nullable = false)
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    @Column(name = "pages", nullable = false)
    public int getPages(){
        return this.pages;
    }
    public void setPages(int pages){
        this.pages = pages;
    }
}
