package com.huongnguyen.booktest.repository;

import com.huongnguyen.booktest.dto.response.BookDto;
import com.huongnguyen.booktest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
    select new com.huongnguyen.booktest.dto.response.BookDto(
        b.title,
        b.publishedDate,
        b.author.id,
        b.price
    )
    from Book b where
    (:authorId is null or b.author.id = :authorId) and
    (:publishedDate is null or b.publishedDate >= :publishedDate) and
    (:title is null or b.title ilike :title) and
    (:price is null or b.price >= :price)
    """)
    List<BookDto> filterBooks(@Param("title") String title,
                              @Param("publishedDate") LocalDate publishedDate,
                              @Param("authorId") Long authorId,
                              @Param("price") BigDecimal price);
}
