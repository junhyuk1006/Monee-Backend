package com.example.moneebackend.dto.transaction;

import com.example.moneebackend.domain.transaction.Transaction;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransactionResponseDto {
    private final String categoryType;
    private final Integer amount;
    private final String memo;
    private final String detail;
    private final String method;
    private final LocalDateTime date;

    public TransactionResponseDto(Transaction transaction){
        this.categoryType = transaction.getCategory().getType();
        this.amount = transaction.getAmount();
        this.memo = transaction.getMemo();
        this.detail = transaction.getDetail();
        this.method = transaction.getMethod();
        this.date = transaction.getDate();
    }
}
