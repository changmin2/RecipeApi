package com.example.springApi.controller;

import com.example.springApi.domain.dto.MemberLoginRequestDto;
import com.example.springApi.domain.dto.TokenInfo;
import com.example.springApi.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        System.out.println(memberId.toString()+" "+password.toString());
        TokenInfo tokenInfo = memberService.login(memberId, password);
        System.out.println(tokenInfo.toString());
        return tokenInfo;
    }
}
