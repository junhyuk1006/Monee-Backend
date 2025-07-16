package com.example.moneebackend.service.transaction;

import com.example.moneebackend.domain.category.Category;
import com.example.moneebackend.domain.category.CategoryRepository;
import com.example.moneebackend.domain.transaction.Transaction;
import com.example.moneebackend.domain.transaction.TransactionRepository;
import com.example.moneebackend.domain.user.User;
import com.example.moneebackend.dto.transaction.TransactionCreateRequestDto;
import com.example.moneebackend.dto.transaction.TransactionResponseDto;
import com.example.moneebackend.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final 이나 @NonNull (NotNull X) 이 붙은 필드 생성자를 자동으로 만들어줌
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public void create(TransactionCreateRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        Category category = categoryRepository.findByTypeAndName(requestDto.getCategoryType(), requestDto.getCategoryName())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다")); // CustomException 생성하면 좋음

        Transaction transaction = Transaction.builder()
                            .user(userDetails.getUser())
                            .category(category)
                            .amount(requestDto.getAmount())
                            .memo(requestDto.getMemo())
                            .detail(requestDto.getDetail())
                            .method(requestDto.getMethod())
                            .date(requestDto.getDate()).build();

        transactionRepository.save(transaction);
    }

    public List<TransactionResponseDto> getTransactionByDateRange(User user, LocalDateTime start, LocalDateTime end){
        return transactionRepository.findByUserAndDateBetween(user,start,end)
                .stream()
                .map(TransactionResponseDto::new) // 스트림의 각 Transaction 객체를 TransactionResponseDto 객체로 변환하는 작업
                .toList();
    }
}
