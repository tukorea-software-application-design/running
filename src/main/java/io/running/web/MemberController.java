package io.running.web;

import com.google.firebase.auth.FirebaseToken;
import io.running.dto.req.MemberEditReqDto;
import io.running.dto.req.MemberLocalRegisterReqDto;
import io.running.dto.resp.MemberLocalRegisterRespDto;
import io.running.domain.member.Member;
import io.running.service.MemberResisterDto;
import io.running.service.MemberService;
import io.running.service.Oauth2Service;
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
    public ResponseEntity<MemberLocalRegisterRespDto> localMemberResister(@RequestBody MemberLocalRegisterReqDto memberLocalRegisterReqDto) {
        MemberLocalRegisterRespDto memberRegisterRespDto = memberService.resister(new MemberResisterDto(memberLocalRegisterReqDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberRegisterRespDto);
    }

    //배포 회원 가입
//    @PostMapping("")
//    public ResponseEntity<MemberLocalRegisterRespDto> registerMember(@RequestHeader("Authorization") String header,
//                                                                     @RequestBody @Valid MemberLocalRegisterReqDto memberLocalRegisterReqDto) {
//        // TOKEN을 가져온다.
//        FirebaseToken decodedToken = memberService.decodeToken(header);
//        // 사용자를 등록한다.
//        MemberRegisterDto memberRegisterDto = new MemberRegisterDto(decodedToken.getUid(), decodedToken.getName(),
//                decodedToken.getEmail(), memberRegisterRequestDto.getNickname(), decodedToken.getPicture());
//
//        MemberRegisterResponseDto responseDto = memberService.register(
//                memberRegisterDto);
//
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(responseDto);
//    }

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
