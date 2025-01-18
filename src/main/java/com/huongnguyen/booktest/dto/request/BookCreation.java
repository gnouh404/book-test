package com.huongnguyen.booktest.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookCreation(
        @NotBlank(message = "Title cannot be blank")
        String title,

        @NotNull(message = "Author ID is required")
        Long authorId,

        @PastOrPresent(message = "Published date cannot be in the future")
        LocalDate publishedDate,

        @NotBlank(message = "ISBN is required")
        String isbn,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be greater than 0")
        BigDecimal price
) { }
