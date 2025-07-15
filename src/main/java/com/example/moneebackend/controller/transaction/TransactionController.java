package com.example.moneebackend.controller.transaction;

import com.example.moneebackend.dto.transaction.TransactionCreateRequestDto;
import com.example.moneebackend.security.CustomUserDetails;
import com.example.moneebackend.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody TransactionCreateRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        transactionService.create(requestDto,userDetails);
        return ResponseEntity.ok("저장 성공");
    }
}
