package com.example.bookService.service;

import com.example.bookService.dto.BookDTO;
import com.example.bookService.exception.BookNoteFound;
import com.example.bookService.model.BookModel;
import com.example.bookService.repository.BookRepository;
import com.example.bookService.util.BookStoreUser;
import com.example.bookService.util.Response;
import com.example.bookService.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Service
public class BookServices implements IBookServices{

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    Token tokenUtil;

    @Override
    public Response addBook(String token,BookDTO bookDTO) {
       if (isUserPresent(token)!=null){
           BookModel bookModel=new BookModel(bookDTO);
           bookRepository.save(bookModel);
           return new Response("Book added successfully", 200, bookModel);
       }
        throw new BookNoteFound(400,"User note found !");
    }

    @Override
    public Response readBook(String token, long bookId) {
        if (isUserPresent(token)!=null){
            Optional<BookModel> bookModel=bookRepository.findById(bookId);
            if (bookModel.isPresent()){
                return new Response("Fetching Book", 200, bookModel.get());
            }
            throw new BookNoteFound(400,"Book note found !");
        }
        throw new BookNoteFound(400,"User note found !");
    }

    @Override
    public BookModel getBook(long bookId) {
        Optional<BookModel> bookModel=bookRepository.findById(bookId);
        if (bookModel.isPresent()){
            return bookModel.get();
        }
        throw new BookNoteFound(400,"Book note found !");
    }

    @Override
    public Response updateBook(String token, long bookId, BookDTO bookDTO) {
        if (isUserPresent(token)!=null){
            Optional<BookModel> bookModel=bookRepository.findById(bookId);
            if (bookModel.isPresent()) {
                bookModel.get().setBookName(bookDTO.getBookName());
                bookModel.get().setAuthor(bookDTO.getAuthor());
                bookModel.get().setDescription(bookDTO.getDescription());
                bookRepository.save(bookModel.get());
                return new Response("updating book details",200,bookModel.get());
             }
            throw new BookNoteFound(400,"Book note found !");
        }
        throw new BookNoteFound(400,"User note found !");
    }

    @Override
    public Response deleteBook(String token, long bookId) {
        if (isUserPresent(token)!=null) {
            Optional<BookModel> bookModel = bookRepository.findById(bookId);
            if (bookModel.isPresent()) {
                bookRepository.delete(bookModel.get());
                return new Response("Deleting book.",200,bookModel.get());
            }
            throw new BookNoteFound(400,"Book note found !");
        }
        throw new BookNoteFound(400,"User note found !");
    }

    @Override
    public Response changeBookPrice(String token, long bookId, double price) {
        if (isUserPresent(token)!=null) {
            Optional<BookModel> bookModel = bookRepository.findById(bookId);
            if (bookModel.isPresent()) {
                bookModel.get().setPrice(price);
                bookRepository.save(bookModel.get());
                return new Response("Price changed successfully",200,bookModel.get());
            }
            throw new BookNoteFound(400,"Book note found !");
        }
        throw new BookNoteFound(400,"User note found !");
    }

    @Override
    public Response changeBookQuantity( long bookId, int quantity) {
            Optional<BookModel> bookModel = bookRepository.findById(bookId);
            if (bookModel.isPresent()) {
                bookModel.get().setQuantity(quantity);
                bookRepository.save(bookModel.get());
                return new Response("Quantity changed successfully",200,bookModel.get());
            }
        throw new BookNoteFound(400,"Book note found !");

    }


    public BookStoreUser isUserPresent(String token){
        return restTemplate.getForObject("http://localhost:9091/user/verify/"+token,BookStoreUser.class);
    }
}
