package com.huongnguyen.booktest.controller;

import com.huongnguyen.booktest.dto.request.AuthorCreation;
import com.huongnguyen.booktest.dto.response.ApiResponse;
import com.huongnguyen.booktest.service.AuthorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(@Qualifier("authorServiceImpl") AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createAuthor(@RequestBody AuthorCreation request) {
        authorService.createAuthor(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity
                .ok(new ApiResponse(HttpStatus.OK.value(), "Delete author successfully"));
    }

}
