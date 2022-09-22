package com.example.bookService.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class BookNoteFound extends RuntimeException {
    private long errorCode;
    private String statusMessage;

    public BookNoteFound(long errorCode, String statusMessage) {
        super(statusMessage);
        this.errorCode = errorCode;
        this.statusMessage = statusMessage;
    }
}
