package com.qyf.spring_study.web;

import com.qyf.spring_study.domain.Book;
import com.qyf.spring_study.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String list(Model model){
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books"; // find the books.html under resources
    }
    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model){
        Book book = bookService.findOne(id).get();
        if(book == null){
            book = new Book();
        }
        model.addAttribute("book", book);
        return "book";
    }

    /**
     * Rediret to input pafe
     * @return
     */
    @GetMapping("/books/input")
    public String inputPage(Model model){
        model.addAttribute("book", new Book());
        return "input";
    }

    @GetMapping("/books/{id}/input")
    public String inputEditPage(@PathVariable long id, Model model){
        Book book = bookService.findOne(id).get();
        model.addAttribute("book", book);
        return "input";
    }

    /**
     * submit a book information
     * POST -->redirect -->GET
     * MuST use flash attribute, can not use model.attribute only
     */
    @PostMapping("/books")
    public String post(
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam String description,
            @RequestParam int status,
            final RedirectAttributes attributes
            ){
        Book book= new Book();
        book.setName(name);
        book.setStatus(status);
        book.setDescription(description);
        book.setAuthor(author);
        book.setId(id);
        bookService.save(book);
        attributes.addFlashAttribute("message", "<<" + book.getName() + ">> information submitted successfully");
        return "redirect:/books";
    }
}
