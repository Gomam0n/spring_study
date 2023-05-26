package com.qyf.spring_study.web;

import com.qyf.spring_study.domain.Book;
import com.qyf.spring_study.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BookApp {
    @Autowired
    private BookService bookService;

    /**
     *
     * @return
     */
    @GetMapping("/books")
    public List<Book> getAll(){

        return bookService.findAll();

    }

    /**
     * Can use Book as parameter directly
     * @param name
     * @param author
     * @param description
     * @param status
     * @return
     */
    @PostMapping("/books")
    public Book post(@RequestParam String name,
                     @RequestParam String author,
                     @RequestParam String description,
                     @RequestParam int status){
        Book book = new Book();
        book.setStatus(status);
        book.setName(name);
        book.setDescription(description);
        book.setAuthor(author);
        return bookService.save(book);
    }

    @GetMapping("/books/{id}")
    public Optional<Book> getOne(@PathVariable long id){
        return bookService.findOne(id);
    }

    @PutMapping("/books")
    public Book update(@RequestParam long id,
                        @RequestParam String name,
                       @RequestParam String author,
                       @RequestParam String description,
                       @RequestParam int status){
        Book book = new Book();
        book.setId(id);
        book.setStatus(status);
        book.setName(name);
        book.setDescription(description);
        book.setAuthor(author);
        return bookService.save(book);
    }
    @DeleteMapping("/books/{id}")
    public void deleteOne(@PathVariable long id){
        bookService.deleteOne(id);
    }
}
