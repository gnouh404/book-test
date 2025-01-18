package com.huongnguyen.booktest.dto.response;

public record ApiResponse(
        Integer status,
        String message
) { }
