package com.example.moneebackend.controller.user;

import com.example.moneebackend.dto.user.UserLoginRequestDto;
import com.example.moneebackend.dto.user.UserLoginResponseDto;
import com.example.moneebackend.dto.user.UserSignupRequestDto;
import com.example.moneebackend.dto.user.UserUpdateRequestDto;
import com.example.moneebackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDto requestDto) {
        userService.register(requestDto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestDto) {
        return ResponseEntity.ok(userService.login(requestDto));
    }

    @GetMapping("/me")
    public String getMyInfo(@AuthenticationPrincipal UserDetails userDetails){
        return "현재 로그인한 유저 정보 : " + userDetails.getUsername();
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestBody UserUpdateRequestDto requestDto){
        userService.updateUser(userDetails.getUsername(),requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/me")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetails userDetails){
        userService.deleteUser(userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
