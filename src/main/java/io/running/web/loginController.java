package io.running.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.running.service.Oauth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class loginController {

    private final Oauth2Service oauth2Service;

    @GetMapping("/login/oauth2/code/kakao")
    public String kakao(@RequestParam String code) throws JsonProcessingException {
        System.out.println(code);
        return oauth2Service.requestKakaoAccessToken(code);
    }

    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<String> google(@RequestParam String code) {
        System.out.println(code);

        ResponseEntity<String> stringResponseEntity = oauth2Service.requestGoogleAccessToken(code);

        return stringResponseEntity;
    }
}
