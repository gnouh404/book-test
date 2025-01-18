package com.huongnguyen.booktest.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookDto(
        String title,
        LocalDate publishedDate,
        Long authorId,
        BigDecimal price
) { }
