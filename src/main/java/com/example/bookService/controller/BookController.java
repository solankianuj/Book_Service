package com.example.bookService.controller;

import com.example.bookService.dto.BookDTO;
import com.example.bookService.model.BookModel;
import com.example.bookService.service.BookServices;
import com.example.bookService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookServices bookServices;

    @PostMapping("/addBook")
    public ResponseEntity<Response> addingBook(@RequestHeader String token, @RequestBody BookDTO bookDTO){
        Response response=bookServices.addBook(token ,bookDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getBookDetails")
    public ResponseEntity<Response> gettingBook(@RequestHeader String token, @RequestParam long bookId){
        Response response=bookServices.readBook(token ,bookId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/updateBook")
    public ResponseEntity<Response> updatingBook(@RequestHeader String token,@RequestParam long bookId, @RequestBody BookDTO bookDTO){
        Response response=bookServices.updateBook(token,bookId ,bookDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/deleteBook")
    public ResponseEntity<Response> deletingBook(@RequestHeader String token, @RequestParam long bookId){
        Response response=bookServices.deleteBook(token ,bookId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/changeBookPrice")
    public ResponseEntity<Response> changingBookPrice(@RequestHeader String token, @RequestParam long bookId,@RequestParam double price){
        Response response=bookServices.changeBookPrice(token,bookId,price);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/changeBookQuantity/{bookId}/{quantity}")
    public ResponseEntity<Response> changingBookQuantity( @PathVariable long bookId,@PathVariable int quantity){
        Response response=bookServices.changeBookQuantity(bookId,quantity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getBook/{bookId}")
    public ResponseEntity<BookModel> gettingBook(@PathVariable long bookId){
        BookModel response=bookServices.getBook(bookId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
