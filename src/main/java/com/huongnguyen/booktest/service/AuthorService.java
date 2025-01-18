package com.huongnguyen.booktest.service;

import com.huongnguyen.booktest.dto.request.AuthorCreation;

public interface AuthorService {
    void deleteAuthorById(Long id);
    void createAuthor(AuthorCreation request);
}
