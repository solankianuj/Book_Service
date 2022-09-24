package com.example.bookService.model;


import com.example.bookService.dto.BookDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="bookData")
@Data
public class BookModel {
    @Id
    @GenericGenerator(name = "bookData",strategy = "increment")
    @GeneratedValue(generator = "bookData")
    private Long bookId;
    private String bookName;
    private String author;
    private String description;
    @Lob
    private byte[] bookLogo;
    private double price;
    private int quantity;

    public BookModel(BookDTO bookDTO) {
        this.bookName=bookDTO.getBookName();
        this.author= bookDTO.getAuthor();
        this.description=bookDTO.getDescription();
        this.price=bookDTO.getPrice();
        this.quantity=bookDTO.getQuantity();
    }

    public BookModel() {

    }
}
