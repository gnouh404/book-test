package com.huongnguyen.booktest.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookListResponse(
        String title,
        LocalDate publishedDate,
        String isbn,
        BigDecimal price,
        Long authorId
) { }
