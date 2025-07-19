package com.example.moneebackend.service.transaction;

import com.example.moneebackend.domain.category.Category;
import com.example.moneebackend.domain.category.CategoryRepository;
import com.example.moneebackend.domain.transaction.Transaction;
import com.example.moneebackend.domain.transaction.TransactionRepository;
import com.example.moneebackend.domain.user.User;
import com.example.moneebackend.dto.transaction.TransactionRequestDto;
import com.example.moneebackend.dto.transaction.TransactionResponseDto;
import com.example.moneebackend.security.CustomUserDetails;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor // final 이나 @NonNull (NotNull X) 이 붙은 필드 생성자를 자동으로 만들어줌
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public void create(TransactionRequestDto requestDto, User user){
        // 카테고리 이름이 중복되지 않는다면 굳이 Type 까지 가져올 필요는 없어 보임
        Category category = categoryRepository.findByTypeAndName(requestDto.getCategoryType(), requestDto.getCategoryName())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다")); // CustomException 생성하면 좋음

        Transaction transaction = Transaction.builder()
                            .user(user)
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

    @Transactional // 변경 감지를 위해 꼭 필요
    public void update(Long transactionId, User user, TransactionRequestDto requestDto){
        Transaction transaction = transactionRepository.findByIdAndUser(transactionId,user)
                .orElseThrow(() -> new EntityNotFoundException("해당 트랜잭션이 존재하지 않습니다"));

        Category category = categoryRepository.findByTypeAndName(requestDto.getCategoryType(), requestDto.getCategoryName())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다")); // CustomException 생성하면 좋음

        transaction.setCategory(category);
        transaction.setAmount(requestDto.getAmount());
        transaction.setMemo(requestDto.getMemo());
        transaction.setDetail(requestDto.getDetail());
        transaction.setMethod(requestDto.getMethod());
        transaction.setDate(requestDto.getDate());
    }

    public void delete(Long transactionId, User user){
        Transaction transaction = transactionRepository.findByIdAndUser(transactionId,user)
                .orElseThrow(() -> new EntityNotFoundException("해당 트랜잭션이 존재하지 않습니다"));

        transactionRepository.delete(transaction);
    }
}
