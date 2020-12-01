package brad.chamberlain.library.controller;

import brad.chamberlain.library.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookHtmlController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public String getAllBooks(Model model)
    {

        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("message", "Hello message");
        return "allbooks";
    }
}
