package com.example.moneebackend.dto.transaction;

import com.example.moneebackend.domain.transaction.Transaction;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class TransactionResponseDto {
    private final Long id;
    private final String categoryType;
    private final String categoryName;
    private final Integer amount;
    private final String memo;
    private final String detail;
    private final String method;
    private final LocalDateTime date;

    public TransactionResponseDto(Transaction transaction){
        this.id = transaction.getId();
        this.categoryType = transaction.getCategory().getType();
        this.categoryName = transaction.getCategory().getName();
        this.amount = transaction.getAmount();
        this.memo = transaction.getMemo();
        this.detail = transaction.getDetail();
        this.method = transaction.getMethod();
        this.date = transaction.getDate();
    }
}
