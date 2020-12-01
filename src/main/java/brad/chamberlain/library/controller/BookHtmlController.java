package brad.chamberlain.library.controller;

import brad.chamberlain.library.model.Book;
import brad.chamberlain.library.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class BookHtmlController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public String getAllBooks(Model model)
    {
        model.addAttribute("books", bookRepository.findAll());
        return "books/all";
    }

    @GetMapping("/books/new")
    public String newBook(Model model)
    {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping("/books/new")
    public String createBook(@Valid Book book, BindingResult result, Model model)
    {
        bookRepository.save(book);
        model.addAttribute("message", "Book " + book.getTitle() + " has been added");
        return getAllBooks(model);
    }

    @GetMapping("/books/{id}")
    public String showBook(@PathVariable(value = "id") Long bookId, Model model)
            throws BookNotFoundException
    {
        setBookModelAttribute(bookId, model);
        return "books/show";
    }


    @GetMapping("/books/{id}/edit")
    public String editBook(@PathVariable("id") long bookId, Model model)
            throws BookNotFoundException
    {
        setBookModelAttribute(bookId, model);
        return "/books/edit";
    }

    @PostMapping("/books/{id}/update")
    public String updateBook(@PathVariable("id") long bookId, @Valid Book book,
                             BindingResult result, Model model)
            throws BookNotFoundException {
        if (result.hasErrors()) {
            book.setId(bookId);
            model.addAttribute("book", book);
            return editBook(bookId, model);
        }
        bookRepository.save(book);
        return showBook(bookId, model);
    }

    @GetMapping("/books/{id}/delete")
    public String deleteBook(@PathVariable("id") long bookId, Model model) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        bookRepository.delete(book);
        model.addAttribute("message", "Book " + book.getTitle() + " has been deleted");
        return getAllBooks(model);
    }

    private void setBookModelAttribute(@PathVariable("id") Long bookId, Model model)
            throws BookNotFoundException {
        if (!model.containsAttribute("book")) {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new BookNotFoundException(bookId));
            model.addAttribute("book", book);
        }
    }

    private class BookNotFoundException extends Exception{
        public BookNotFoundException(Long id)
        {
            super("Book not found with id: " + id);
        }
    }
}
