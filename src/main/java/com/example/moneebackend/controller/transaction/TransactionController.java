package com.example.moneebackend.controller.transaction;

import com.example.moneebackend.domain.transaction.Transaction;
import com.example.moneebackend.dto.transaction.TransactionCreateRequestDto;
import com.example.moneebackend.dto.transaction.TransactionResponseDto;
import com.example.moneebackend.security.CustomUserDetails;
import com.example.moneebackend.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public List<TransactionResponseDto> getTransactionByDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
                                                                  @AuthenticationPrincipal CustomUserDetails userDetails){
        return transactionService.getTransactionByDateRange(userDetails.getUser(),start,end);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody TransactionCreateRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        transactionService.create(requestDto,userDetails);
        return ResponseEntity.ok("저장 성공");
    }
}
