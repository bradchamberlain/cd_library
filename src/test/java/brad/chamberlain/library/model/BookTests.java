package brad.chamberlain.library.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookTests {

    @Test
    public void testBookConstructor()
    {
        Book book = new Book();
        assertNotNull(book);
        book = new Book("Test Title", "Test Author", 0);
        assertEquals("Test Title", book.getTitle());
    }
}
