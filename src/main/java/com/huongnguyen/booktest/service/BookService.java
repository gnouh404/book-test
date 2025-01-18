package com.huongnguyen.booktest.service;

import com.huongnguyen.booktest.dto.request.BookCreation;
import com.huongnguyen.booktest.dto.request.BookUpdateRequest;
import com.huongnguyen.booktest.dto.response.BookDetailsResponse;
import com.huongnguyen.booktest.dto.response.BookDto;
import com.huongnguyen.booktest.dto.response.BookListResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void createBook(BookCreation request);
    BookDetailsResponse getBookDetails(Long id);
    List<BookListResponse> getAllBooks();
    List<BookDto> filterBooks(String title, LocalDate publishedDate, Long authorId, BigDecimal price);
    void updateBook(Long id, BookUpdateRequest request);
    void deleteBook(Long id);
}
