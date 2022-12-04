package io.running.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.running.dto.req.MemberEditReqDto;
import io.running.dto.req.MemberRegisterReqDto;
import io.running.dto.resp.MemberRegisterRespDto;
import io.running.domain.member.Member;
import io.running.service.*;
import io.running.service.dto.MemberKakaoUserInfoDto;
import io.running.service.dto.MemberResisterDto;
import io.running.service.dto.MemberProdResisterReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final Oauth2Service oauth2Service;

    @PostMapping("/local")
    public ResponseEntity<MemberRegisterRespDto> localMemberResister(@RequestBody MemberRegisterReqDto memberRegisterReqDto) {
        MemberRegisterRespDto memberRegisterRespDto = memberService.resister(new MemberResisterDto(memberRegisterReqDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberRegisterRespDto);
    }

//    배포 회원 가입
    @PostMapping("")
    public ResponseEntity<MemberRegisterRespDto> registerMember(@RequestHeader("Authorization") String header,
                                                                @RequestBody @Valid MemberProdResisterReqDto memberProdResisterReqDto) throws JsonProcessingException {

        MemberKakaoUserInfoDto kakaoUserInfo = oauth2Service.getKakaoUserInfo(header);
        MemberRegisterRespDto memberRegisterRespDto = memberService.resister(new MemberResisterDto(memberProdResisterReqDto, kakaoUserInfo));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberRegisterRespDto);
    }

    @GetMapping("/me")
    public ResponseEntity<MemberRegisterRespDto> login(Authentication authentication) {
        Member member = getMember(authentication);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MemberRegisterRespDto(member));
    }

    @PatchMapping("/me")
    public ResponseEntity editMember(Authentication authentication, @RequestBody MemberEditReqDto memberEditReqDto) {
        Member member = (Member) authentication.getPrincipal();
        memberService.editInfo(member, memberEditReqDto);

        return ResponseEntity
                .noContent()
                .build();
    }

    private Member getMember(Authentication authentication) {
        return (Member) authentication.getPrincipal();
    }
}
