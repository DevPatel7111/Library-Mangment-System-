package org.dev.JBDL_Lec14.repositary;

import org.dev.JBDL_Lec14.model.Book;
import org.dev.JBDL_Lec14.model.BookType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository <Book,Integer> {
    List<Book> findByTitle(String title);

    List<Book> findByTitleContaining(String title);

    List<Book> findByBookType(BookType bookType);

    List<Book> findByBookNo(String bookNo);
}