package org.dev.JBDL_Lec14.controller;

import jakarta.validation.Valid;
import org.dev.JBDL_Lec14.dto.BookRequest;
import org.dev.JBDL_Lec14.model.Book;
import org.dev.JBDL_Lec14.model.BookFilterType;
import org.dev.JBDL_Lec14.model.Operator;
import org.dev.JBDL_Lec14.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")

public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/addbook")
    public Book addbook(@RequestBody @Valid BookRequest bookRequest){
// validato are donet by deoedendcny
//         for busunes logic create s servic --> bookserviceclass

       Book book =  bookService.addbook(bookRequest);

// ti get actual required adata
      return  book;
    }
    @GetMapping("/filter")
    public List<Book> filter(@RequestParam ("filterBy") BookFilterType bookFilterType,
                             @RequestParam("operator") Operator operator ,
                             @RequestParam ("value") String value){

return bookService.filter(bookFilterType,operator,value);
}
}