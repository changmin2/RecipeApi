package com.example.springApi.controller;

import com.example.springApi.domain.dto.MemberLoginRequestDto;
import com.example.springApi.domain.dto.RecipeRequestDto;
import com.example.springApi.domain.dto.TokenInfo;
import com.example.springApi.domain.member.Clip;
import com.example.springApi.domain.member.Member;
import com.example.springApi.domain.recipe.Recipes;
import com.example.springApi.service.recipe.RecipeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.springApi.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.example.springApi.utils.SecurityUtils.TOKEN_HEADER_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.HashMap;
import java.util.List;
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
    //아이디 중복 체크
    @PostMapping("/duplicate")
    public boolean duplicate(@RequestBody Map<String,String> map){
        return memberService.duplicate(map.get("email"));
    }

    @GetMapping("/me")
    public Map<String,String> getMe(HttpServletRequest request){
        System.out.println("me 진입");
        String authroizationHeader = request.getHeader(AUTHORIZATION);
        if(authroizationHeader == null || !authroizationHeader.startsWith(TOKEN_HEADER_PREFIX)){
            throw new RuntimeException("JWT Token이 존재하지 않습니다.");
        }

        String accessToken = authroizationHeader.substring(TOKEN_HEADER_PREFIX.length());
        Map<String,String> json = new HashMap<>();
        Member member= memberService.getMe(accessToken);
        System.out.println("me: "+member.toString());
        json.put("username",member.getMemberId());
        json.put("password",member.getPassword());
        return json;

    }

    @PostMapping("/join")
    public String join(@RequestBody Map<String,String> user){
        String memberId = user.get("memberId");
        String password = passwordEncoder.encode(user.get("password"));
        return memberService.join(memberId,password);
    }

    @GetMapping("/refresh")
    public TokenInfo refresh(HttpServletRequest request, HttpServletResponse response){
        String authroizationHeader = request.getHeader(AUTHORIZATION);

        if(authroizationHeader == null || !authroizationHeader.startsWith(TOKEN_HEADER_PREFIX)){
            throw new RuntimeException("JWT Token이 존재하지 않습니다.");
        }
        String refreshToken = authroizationHeader.substring(TOKEN_HEADER_PREFIX.length());
        return memberService.refresh(refreshToken);
    }



}
