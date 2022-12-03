package io.running.contorller.running;

import io.running.contorller.running.dto.req.RunningCreateReqDto;
import io.running.contorller.running.dto.resp.RunningCreateRespDto;
import io.running.contorller.running.dto.resp.RunningRetrieveRespDto;
import io.running.domain.member.Member;
import io.running.service.RunningService;
import io.running.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/{runningId}")
    public ResponseEntity<RunningRetrieveRespDto> retrieveOneRunning(@PathVariable Long runningId, HttpServletRequest request) {
        String header = RequestUtil.getAuthorizationToken(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(runningService.retrieveRunning(runningId, header));
    }

    private Member getMember(Authentication authentication) {
        return (Member) authentication.getPrincipal();
    }
}
