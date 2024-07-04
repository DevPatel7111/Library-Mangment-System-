package org.dev.JBDL_Lec14.service;


import org.dev.JBDL_Lec14.dto.BookRequest;
import org.dev.JBDL_Lec14.model.*;
import org.dev.JBDL_Lec14.repositary.AuthorRepo;
import org.dev.JBDL_Lec14.repositary.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private BookRepo bookRepo;

    public Book addbook(BookRequest bookRequest) {
        Author authorfromdb = authorRepo.getAuthorByEmail(bookRequest.getAuthorEmail());

        if (authorfromdb == null) {
            authorfromdb = authorRepo.save(bookRequest.toAuthor());
        }
        Book book = bookRequest.toBook();
        book.setAuthor(authorfromdb);
        return bookRepo.save(book);
    }

    ;
    public List<Book> filter(BookFilterType bookFilterType, Operator operator, String value) {
        switch (bookFilterType){
            case BOOK_TITLE :
                switch (operator){
                    case EQUALS :
                        return bookRepo.findByTitle(value);
                    case LIKE:
                        return bookRepo.findByTitleContaining(value);
                    default:
                        return new ArrayList<>();
                }
            case BOOK_TYPE:
                switch (operator) {
                    case EQUALS:
                        return bookRepo.findByBookType(BookType.valueOf(value));
                }
            case BOOK_NO:
                switch (operator) {
                    case EQUALS:
                        return bookRepo.findByBookNo(value);
                }

        }
        return new ArrayList<>();
    }

    public void updateBookData(Book bookFromDb) {
        bookRepo.save(bookFromDb);
    }
}
