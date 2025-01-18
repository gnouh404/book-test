package com.huongnguyen.booktest.service.implement;

import com.huongnguyen.booktest.dto.request.AuthorCreation;
import com.huongnguyen.booktest.entity.Author;
import com.huongnguyen.booktest.exception.AppException;
import com.huongnguyen.booktest.exception.ErrorCode;
import com.huongnguyen.booktest.repository.AuthorRepository;
import com.huongnguyen.booktest.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service("authorServiceImpl")
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public void deleteAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.AUTHOR_NOT_FOUND));

        authorRepository.delete(author);
    }

    @Transactional
    @Override
    public void createAuthor(AuthorCreation request) {
        Author author = new Author();

        author.setName(request.name());
        author.setNationality(request.nationality());

        authorRepository.save(author);
    }
}
