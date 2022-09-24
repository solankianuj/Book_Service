package com.example.bookService.service;

import com.example.bookService.dto.BookDTO;
import com.example.bookService.model.BookModel;
import com.example.bookService.util.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

public interface IBookServices {
    public Response addBook(String token,BookDTO bookDTO);
    public Response addLogo(String token, long bookId, MultipartFile logo) throws IOException, SQLException;
    public Response readBook(String token, long bookId);
    public  BookModel getBook(long bookId);
    public Response updateBook(String token,long bokId,BookDTO bookDTO);
    public Response deleteBook(String token,long bookId);
    public  Response changeBookPrice(String token,long bookId,double price);
    public  Response changeBookQuantity(long bookId,int quantity);

}
