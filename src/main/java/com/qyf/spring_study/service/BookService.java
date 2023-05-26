package com.qyf.spring_study.service;

import com.qyf.spring_study.domain.Book;
import com.qyf.spring_study.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    /**
     * find all books
     * @return
     */
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    /**
     * submit(add or update) a new book
     * @param book
     * @return
     */
    public Book save(Book book){
        return bookRepository.save(book);
    }

    /**
     * find one book
     *
     * @param id
     * @return
     */
    public Optional<Book> findOne(long id){
        return bookRepository.findById(id);
    }

    public void deleteOne(long id){
        bookRepository.deleteById(id);
    }
}
