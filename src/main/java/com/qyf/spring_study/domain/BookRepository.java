package com.qyf.spring_study.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);


    // function name is important. do not need to implement this function!
    List<Book> findByAuthor(String author);

    List<Book> findByAuthorAndStatus(String author, int status);

    List<Book> findByDescriptionEndsWith(String des);

    List<Book> findByDescriptionContains(String des);

    /**
     * The first query language is not sql. The second is sql.
     *
     * @param len
     * @return
     */
    //@Query("select b from Book b where length(b.name) > ?1")
    @Query(value = "select * from book where length(name) > ?1", nativeQuery = true)
    List<Book> findByJPQL(int len);


    @Modifying
    @Transactional
    @Query("update Book b set b.status=?1 where b.id = ?2")
    int updateByJPQL(int status, long id);

    @Modifying
    @Transactional
    @Query("delete from Book b where b.id = ?1")
    int deleteByJPQL(long id);
}
