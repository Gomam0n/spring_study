package com.qyf.spring_study.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // function name is important. do not need to implement this function!
    List<Book> findByAuthor(String author);

    List<Book> findByAuthorAndStatus(String author, int status);

    List<Book> findByDescriptionEndsWith(String des);

    List<Book> findByDescriptionContains(String des);

    /**
     * The first query language is not sql. The second is sql.
     * @param len
     * @return
     */
    //@Query("select b from Book b where length(b.name) > ?1")
    @Query(value = "select * from book where length(name) > ?1", nativeQuery = true)
    List<Book> findByJPQL(int len);

}
