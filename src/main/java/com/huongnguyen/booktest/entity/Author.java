package com.huongnguyen.booktest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_seq_generator")
    @SequenceGenerator(name = "authors_seq_generator", sequenceName = "authors_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String nationality;

    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private List<Book> books;
}
