package com.qyf.spring_study.service;

import com.qyf.spring_study.domain.Book;
import com.qyf.spring_study.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Book> findAllByPage(Pageable pageable){
        return bookRepository.findAll(pageable);
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

    public List<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByAuthorAndStatus(String author, int status){
        return bookRepository.findByAuthorAndStatus(author, status);
    }

    public List<Book> findByDescriptionEndWith(String des){
        return bookRepository.findByDescriptionEndsWith(des);
    }

    public List<Book> findByJPQL(int len){
        return bookRepository.findByJPQL(len);
    }

    /**
     * It is required to add transactional annotation when using update/delete.
     * Transactional here will override transactional in book repository.
     * @param status
     * @param id
     * @return
     */
    @Transactional
    public int updateByJPQL(int status, long id){
        return bookRepository.updateByJPQL(status,id);
    }

    /**
     * Test transactional operations
     * @param status
     * @param id
     * @param uid
     * @return
     */
    //@Transactional
    public int deleteAndUpdate(int status, long id, long uid){
        int dCount = bookRepository.deleteByJPQL(id);

        int uCount = bookRepository.updateByJPQL(status, uid);

        return dCount + uCount;
    }
}
