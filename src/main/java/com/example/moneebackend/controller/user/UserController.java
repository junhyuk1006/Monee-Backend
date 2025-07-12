package com.example.moneebackend.controller.user;

import com.example.moneebackend.dto.user.UserLoginRequestDto;
import com.example.moneebackend.dto.user.UserLoginResponseDto;
import com.example.moneebackend.dto.user.UserSignupRequestDto;
import com.example.moneebackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> singup(@RequestBody UserSignupRequestDto requestDto) {
        userService.register(requestDto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestDto) {
        return ResponseEntity.ok(userService.login(requestDto));
    }
}
