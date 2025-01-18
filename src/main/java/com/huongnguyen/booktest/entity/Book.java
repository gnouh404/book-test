package com.huongnguyen.booktest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_seq_generator")
    @SequenceGenerator(name = "books_seq_generator", sequenceName = "books_id_seq", allocationSize = 1)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Positive(message = "Price must be greater than 0")
    @NotNull(message = "Price cannot be null")
    private BigDecimal price;
}
