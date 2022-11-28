package io.running.contorller.member;

import io.running.contorller.member.dto.req.MemberEditReqDto;
import io.running.contorller.member.dto.req.MemberLocalRegisterReqDto;
import io.running.contorller.member.dto.resp.MemberLocalRegisterRespDto;
import io.running.domain.member.entity.Member;
import io.running.domain.member.service.MemberResisterDto;
import io.running.domain.member.service.MemberService;
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

    @PostMapping("/local")
    public ResponseEntity<MemberLocalRegisterRespDto> localMemberResister(@RequestBody MemberLocalRegisterReqDto memberLocalRegisterReqDto) {
        MemberLocalRegisterRespDto memberRegisterRespDto = memberService.resister(new MemberResisterDto(memberLocalRegisterReqDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberRegisterRespDto);
    }

    @PatchMapping("/me")
    public ResponseEntity editMember(Authentication authentication, @RequestBody MemberEditReqDto memberEditReqDto) {
        Member member = (Member) authentication.getPrincipal();
        memberService.editInfo(member, memberEditReqDto);

        return ResponseEntity
                .noContent()
                .build();
    }
}
