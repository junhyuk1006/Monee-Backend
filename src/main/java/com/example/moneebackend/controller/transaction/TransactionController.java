package com.example.moneebackend.controller.transaction;

import com.example.moneebackend.dto.transaction.TransactionRequestDto;
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
    public List<TransactionResponseDto> getTransactionByDateRange(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
                                                                  @AuthenticationPrincipal CustomUserDetails userDetails){
        return transactionService.getTransactionByDateRange(userDetails.getUser(),start,end);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody TransactionRequestDto requestDto,
                                         @AuthenticationPrincipal CustomUserDetails userDetails){
        transactionService.create(requestDto,userDetails.getUser());
        return ResponseEntity.ok("저장 성공");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long transactionId,
                                         @RequestBody TransactionRequestDto requestDto,
                                         @AuthenticationPrincipal CustomUserDetails userDetails){
        transactionService.update(transactionId,userDetails.getUser(),requestDto);
        return ResponseEntity.ok("수정 성공");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long transactionId,
                                         @AuthenticationPrincipal CustomUserDetails userDetails){
        transactionService.delete(transactionId,userDetails.getUser());
        return ResponseEntity.ok("삭제 성공");
    }
}
