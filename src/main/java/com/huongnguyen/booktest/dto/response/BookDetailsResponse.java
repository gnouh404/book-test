package com.huongnguyen.booktest.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookDetailsResponse(
        String title,
        LocalDate publishedDate,
        String isbn,
        BigDecimal price,
        AuthorResponse author
) { }
