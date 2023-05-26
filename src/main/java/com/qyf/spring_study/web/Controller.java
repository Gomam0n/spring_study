package com.qyf.spring_study.web;

import com.qyf.spring_study.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@org.springframework.stereotype.Controller
@RestController
@RequestMapping("/v1")
public class Controller {
    //@Autowired
    //private Book book;
    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public String hello(){
        return "Hello Spring Boot";
    }

    @GetMapping("/books")
//    @ResponseBody
    public Object getAll(@RequestParam int page, @RequestParam(value = "size",defaultValue = "10") int size){
        Map<String, Object> book = new HashMap<>();
        book.put("name", "bookname");
        book.put("isbn", "123");
        book.put("author", "bookauthor");
        Map<String, Object> book2 = new HashMap<>();
        book2.put("name", "bookname2");
        book2.put("isbn", "1234");
        book2.put("author", "bookauthor2");

        List<Map> contents = new ArrayList<>();
        contents.add(book);
        contents.add(book2);

        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("page", page);
        pageMap.put("size", size);
        pageMap.put("content", contents);

        return pageMap;
    }

    /**
     *
     * @param bid
     * @param username
     * @return
     */
    @GetMapping("/books/{id}/{username:[a-z_]+}")
    public Object getOne(@PathVariable("id") long bid, @PathVariable String username){

        //return book;
        return null;
    }

    @PostMapping("/books")
    public Object post(@RequestParam String name,
                       @RequestParam String author,
                       @RequestParam String isbn){
        Map<String , Object> book = new HashMap<>();
        book.put("name", name);
        book.put("author", author);
        book.put("isbn", isbn);
        return book;
    }
}
