package com.example.moneebackend.dto.transaction;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransactionRequestDto {
    private Integer amount;
    private String memo;
    private String detail;
    private String method;
    private LocalDateTime date;
    private String categoryType;
    private String categoryName;
}
