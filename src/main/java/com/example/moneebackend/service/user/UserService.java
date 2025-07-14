package com.example.moneebackend.service.user;

import com.example.moneebackend.domain.user.User;
import com.example.moneebackend.domain.user.UserRepository;
import com.example.moneebackend.domain.user.UserStatus;
import com.example.moneebackend.dto.user.UserLoginRequestDto;
import com.example.moneebackend.dto.user.UserLoginResponseDto;
import com.example.moneebackend.dto.user.UserSignupRequestDto;
import com.example.moneebackend.dto.user.UserUpdateRequestDto;
import com.example.moneebackend.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public void register(UserSignupRequestDto requestDto) {

        if(userRepository.existsByEmail(requestDto.getEmail())){
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        // Builder 패턴은 객체를 명확하게, 순서 없이, 필요한 값만 골라서 만들 수 있게 도와줌
        // 팩토리 메서드
        User user = User.builder()
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .nickname(requestDto.getNickname())
                .status(UserStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }

    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일이 존재하지 않습니다"));

        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtProvider.generateToken(user.getEmail());

        return new UserLoginResponseDto(token, user.getEmail(), user.getNickname());
    }

    public void updateUser(String email, UserUpdateRequestDto requestDto){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setNickname(requestDto.getNickname());
        // Spring Data JPA 의 save()는 전달받은 엔티티가 새로운 것이면 insert, 이미 존재하는 것이면 update 자동으로 수행
        userRepository.save(user);
    }

    public void deleteUser(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.deactivate();
        userRepository.save(user);
    }
}
