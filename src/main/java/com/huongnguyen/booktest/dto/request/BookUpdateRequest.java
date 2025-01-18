package com.huongnguyen.booktest.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookUpdateRequest(
        String title,
        LocalDate publishedDate,
        Long authorId,
        String isbn,
        BigDecimal price
) { }
