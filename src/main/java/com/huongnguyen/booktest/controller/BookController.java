package com.huongnguyen.booktest.controller;

import com.huongnguyen.booktest.dto.request.BookCreation;
import com.huongnguyen.booktest.dto.request.BookUpdateRequest;
import com.huongnguyen.booktest.dto.response.ApiResponse;
import com.huongnguyen.booktest.dto.response.BookDetailsResponse;
import com.huongnguyen.booktest.dto.response.BookDto;
import com.huongnguyen.booktest.dto.response.BookListResponse;
import com.huongnguyen.booktest.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(@Qualifier("bookServiceImpl") BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createBook(@RequestBody @Valid BookCreation request){
        bookService.createBook(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(HttpStatus.CREATED.value(),"Create a new book successfully"));
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDetailsResponse> getBookDetails(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookDetails(id));
    }

    @GetMapping
    public ResponseEntity<List<BookListResponse>> getAllBooks(){
        return ResponseEntity
                .ok(bookService.getAllBooks());
    }

    @GetMapping("/all-books")
    public ResponseEntity<List<BookDto>> getBooks(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) LocalDate publishedDate,
                                                  @RequestParam(required = false) Long authorId,
                                                  @RequestParam(required = false) BigDecimal price){

        return ResponseEntity
                .ok(bookService.filterBooks(title, publishedDate, authorId, price));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable Long id, @RequestBody BookUpdateRequest request){
        bookService.updateBook(id, request);
        return ResponseEntity
                .ok(new ApiResponse(HttpStatus.OK.value(),"Update a book successfully"));
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity
                .ok(new ApiResponse(HttpStatus.OK.value(),"Delete a book successfully"));
    }
}
