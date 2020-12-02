package brad.chamberlain.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends Exception
{
    public BookNotFoundException(Long id)
    {
        super("Book not found with id: " + id);
    }
}
