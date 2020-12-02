package brad.chamberlain.library.controller;

import brad.chamberlain.library.exceptions.BookNotFoundException;
import brad.chamberlain.library.model.Book;
import brad.chamberlain.library.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookHtmlController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("")
    public String getAllBooks(Model model)
    {
        model.addAttribute("books", bookRepository.findAll());
        return "books/all";
    }

    @GetMapping("/new")
    public String newBook(Model model)
    {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping("/new")
    public String createBook(@Valid Book book, BindingResult result, Model model)
    {
        bookRepository.save(book);
        model.addAttribute("message", "Book " + book.getTitle() + " has been added");
        return getAllBooks(model);
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable(value = "id") Long bookId, Model model)
            throws BookNotFoundException
    {
        setBookModelAttribute(bookId, model);
        return "books/show";
    }


    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") long bookId, Model model)
            throws BookNotFoundException
    {
        setBookModelAttribute(bookId, model);
        return "/books/edit";
    }

    @PostMapping("/{id}/update")
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

    @GetMapping("/{id}/delete")
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
}
