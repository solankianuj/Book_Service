package com.example.bookService.service;

import com.example.bookService.dto.BookDTO;
import com.example.bookService.model.BookModel;
import com.example.bookService.util.Response;

public interface IBookServices {
    public Response addBook(String token,BookDTO bookDTO);
    public Response readBook(String token, long bookId);
    public  BookModel getBook(long bookId);
    public Response updateBook(String token,long bokId,BookDTO bookDTO);
    public Response deleteBook(String token,long bookId);
    public  Response changeBookPrice(String token,long bookId,double price);
    public  Response changeBookQuantity(long bookId,int quantity);

}
