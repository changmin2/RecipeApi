package com.example.springApi.controller;

import com.example.springApi.domain.dto.MemberLoginRequestDto;
import com.example.springApi.domain.dto.TokenInfo;
import com.example.springApi.domain.member.Member;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.springApi.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        Member member = memberService.getMember(memberLoginRequestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다."));
        if(!passwordEncoder.matches(memberLoginRequestDto.getPassword(),member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        TokenInfo tokenInfo = memberService.login(memberLoginRequestDto.getMemberId(), member.getPassword());
        return tokenInfo;
    }

    @PostMapping("/test")
    public String test(){
        return "sucess";
    }

    @PostMapping("/join")
    public String join(@RequestBody Map<String,String> user){
        String memberId = user.get("memberId");
        String password = passwordEncoder.encode(user.get("password"));
        return memberService.join(memberId,password);
    }
}
