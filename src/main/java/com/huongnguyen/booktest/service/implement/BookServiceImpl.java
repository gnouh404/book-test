package com.huongnguyen.booktest.service.implement;

import com.huongnguyen.booktest.dto.request.BookCreation;
import com.huongnguyen.booktest.dto.request.BookUpdateRequest;
import com.huongnguyen.booktest.dto.response.AuthorResponse;
import com.huongnguyen.booktest.dto.response.BookDetailsResponse;
import com.huongnguyen.booktest.dto.response.BookDto;
import com.huongnguyen.booktest.dto.response.BookListResponse;
import com.huongnguyen.booktest.entity.Author;
import com.huongnguyen.booktest.entity.Book;
import com.huongnguyen.booktest.exception.AppException;
import com.huongnguyen.booktest.exception.ErrorCode;
import com.huongnguyen.booktest.repository.AuthorRepository;
import com.huongnguyen.booktest.repository.BookRepository;
import com.huongnguyen.booktest.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service("bookServiceImpl")
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // create book method
    // no need to add @Transactional cause if a value(ISBN) violates unique constraint
    // an exception will be thrown instantly
    public void createBook(BookCreation request){
        Author author = authorRepository.findById(request.authorId())
                .orElseThrow(() -> new AppException(ErrorCode.AUTHOR_NOT_FOUND));

        Book book = new Book();
        book.setTitle(request.title());
        book.setPublishedDate(request.publishedDate());
        book.setAuthor(author);
        book.setIsbn(request.isbn());
        book.setPrice(request.price());

        try {
            bookRepository.save(book);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.ISBN_ALREADY_EXISTS);
        }
    }

    @Override
    public BookDetailsResponse getBookDetails(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

        return new BookDetailsResponse(
                book.getTitle(),
                book.getPublishedDate(),
                book.getIsbn(),
                book.getPrice(),
                new AuthorResponse(
                        book.getAuthor().getName(),
                        book.getAuthor().getNationality()
                )
        );
    }

    @Override
    public List<BookListResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> new BookListResponse(
                        book.getTitle(),
                        book.getPublishedDate(),
                        book.getIsbn(),
                        book.getPrice(),
                        book.getAuthor().getId()
                ))
                .toList();
    }

    @Override
    public List<BookDto> filterBooks(String title, LocalDate publishedDate, Long authorId, BigDecimal price) {
        return bookRepository.filterBooks(title, publishedDate, authorId, price);
    }

    @Transactional
    @Override
    public void updateBook(Long id,BookUpdateRequest request) {

        // check if the book and author of the book want to be modified exists
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));
        Author existingAuthor = authorRepository.findById(request.authorId())
                .orElseThrow(() -> new AppException(ErrorCode.AUTHOR_NOT_FOUND));

        book.setTitle(request.title());
        book.setPublishedDate(request.publishedDate());
        book.setAuthor(existingAuthor);
        book.setIsbn(request.isbn());
        book.setPrice(request.price());

        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));
        bookRepository.delete(book);
    }
}
