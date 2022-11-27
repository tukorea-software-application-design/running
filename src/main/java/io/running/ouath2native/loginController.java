package io.running.ouath2native;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class loginController {

    private final UserService userService;

    @GetMapping("/login/oauth2/code/kakao")
    public String kakao(@RequestParam String code) throws JsonProcessingException {
        System.out.println(code);

        String s = userService.requestKakaoAccessToken(code);

        return s;
    }

    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<String> google(@RequestParam String code) {
        System.out.println(code);

        ResponseEntity<String> stringResponseEntity = userService.requestGoogleAccessToken(code);

        return stringResponseEntity;
    }


}
