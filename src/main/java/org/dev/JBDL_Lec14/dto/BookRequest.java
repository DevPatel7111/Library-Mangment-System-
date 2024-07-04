package org.dev.JBDL_Lec14.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.dev.JBDL_Lec14.model.Author;
import org.dev.JBDL_Lec14.model.Book;
import org.dev.JBDL_Lec14.model.BookType;
@Data
public class BookRequest {

    @NotBlank(message = "fill  every coulmn")
    private String BookTitle;

    @NotBlank(message = "fill  every coulmn")
    private String AuthorName;

    @NotBlank(message = "fill  every coulmn")
    private String BookNo;

    @NotBlank(message = "fill  every coulmn")
    private String AuthorEmail;

    @NotNull(message = "fill  every coulmn")
    private BookType bookType;

    @Positive
    private Integer securityAmount;

    public Author toAuthor() {
        return Author.builder().email(this.AuthorEmail).name(this.AuthorName).build();
    }

    public Book toBook() {
        return Book.builder().
                bookNo(this.BookNo)
                .title(this.BookTitle).
                securityAmount(this.securityAmount).bookType(this.bookType).build()
    ;}

    ;


}