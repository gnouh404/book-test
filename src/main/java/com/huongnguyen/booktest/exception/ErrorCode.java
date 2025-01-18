package com.huongnguyen.booktest.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    AUTHOR_NOT_FOUND(404, "Author not exist"),
    BOOK_NOT_FOUND(404, "Book not exist"),
    ISBN_ALREADY_EXISTS(400, "ISBN already exists"),
    ;

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
