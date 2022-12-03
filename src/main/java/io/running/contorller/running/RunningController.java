package io.running.contorller.running;

import io.running.contorller.running.dto.req.RunningCreateReqDto;
import io.running.contorller.running.dto.resp.RunningCreateRespDto;
import io.running.domain.member.Member;
import io.running.service.RunningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/runnings")
public class RunningController {

    private final RunningService runningService;

    @PostMapping("")
    public ResponseEntity<RunningCreateRespDto> createRunning(Authentication authentication, @RequestBody RunningCreateReqDto createReqDto) {
        Member member = getMember(authentication);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(runningService.createRunning(member, createReqDto));
    }

    private Member getMember(Authentication authentication) {
        return (Member) authentication.getPrincipal();
    }
}
