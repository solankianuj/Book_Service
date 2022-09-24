package com.example.bookService.controller;

import com.example.bookService.dto.BookDTO;
import com.example.bookService.model.BookModel;
import com.example.bookService.service.BookServices;
import com.example.bookService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Purpose-Creating book operation APIs.
 * @author anuj solanki
 * @date 20/09/2022
 * @version 1.0
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookServices bookServices;

    /**
     * Purpose-API to add book.
     * @param token
     * @param bookDTO
     * @return
     */
    @PostMapping("/addBook")
    public ResponseEntity<Response> addingBook(@RequestHeader String token, @RequestBody BookDTO bookDTO){
        Response response=bookServices.addBook(token ,bookDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to add book logo.
     * @param token
     * @param bookId
     * @param logo
     * @return
     * @throws IOException
     */
    @PostMapping("/addLogo")
    public ResponseEntity<Response> addingLogo(@RequestHeader String token, @RequestParam long bookId,@RequestParam MultipartFile logo) throws IOException {
        Response response=bookServices.addLogo(token ,bookId,logo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to fetching book of user .
     * @param token
     * @param bookId
     * @return
     */
    @GetMapping("/getBookDetails")
    public ResponseEntity<Response> gettingBook(@RequestHeader String token, @RequestParam long bookId){
        Response response=bookServices.readBook(token ,bookId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to update book.
     * @param token
     * @param bookId
     * @param bookDTO
     * @return
     */
    @PutMapping("/updateBook")
    public ResponseEntity<Response> updatingBook(@RequestHeader String token,@RequestParam long bookId, @RequestBody BookDTO bookDTO){
        Response response=bookServices.updateBook(token,bookId ,bookDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to delete book.
     * @param token
     * @param bookId
     * @return
     */
    @DeleteMapping("/deleteBook")
    public ResponseEntity<Response> deletingBook(@RequestHeader String token, @RequestParam long bookId){
        Response response=bookServices.deleteBook(token ,bookId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to change book price.
     * @param token
     * @param bookId
     * @param price
     * @return
     */
    @PutMapping("/changeBookPrice")
    public ResponseEntity<Response> changingBookPrice(@RequestHeader String token, @RequestParam long bookId,@RequestParam double price){
        Response response=bookServices.changeBookPrice(token,bookId,price);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to change book quantity.
     * @param bookId
     * @param quantity
     * @return
     */
    @GetMapping("/changeBookQuantity/{bookId}/{quantity}")
    public ResponseEntity<Response> changingBookQuantity( @PathVariable long bookId,@PathVariable int quantity){
        Response response=bookServices.changeBookQuantity(bookId,quantity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to fetch book.
     * @param bookId
     * @return
     */
    @GetMapping("/getBook/{bookId}")
    public ResponseEntity<BookModel> gettingBook(@PathVariable long bookId){
        BookModel response=bookServices.getBook(bookId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
