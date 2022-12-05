package io.running.web;

import io.running.dto.req.MemberEditReqDto;
import io.running.dto.req.MemberRegisterReqDto;
import io.running.dto.resp.MemberLocalRegisterRespDto;
import io.running.domain.member.Member;
import io.running.service.*;
import io.running.service.dto.MemberResisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final Oauth2Service oauth2Service;

    @PostMapping("/local")
    public ResponseEntity<MemberLocalRegisterRespDto> localMemberResister(@RequestBody MemberRegisterReqDto memberRegisterReqDto) {
        MemberLocalRegisterRespDto memberLocalRegisterRespDto = memberService.localResister(new MemberResisterDto(memberRegisterReqDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberLocalRegisterRespDto);
    }

    @GetMapping("/me")
    public ResponseEntity<MemberLocalRegisterRespDto> login(Authentication authentication) {
        Member member = getMember(authentication);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MemberLocalRegisterRespDto(member));
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
