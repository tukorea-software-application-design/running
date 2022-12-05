package io.running.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import io.running.service.Oauth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

        JsonObject obj =new JsonObject();
        String s = oauth2Service.requestKakaoAccessToken(code);

        obj.addProperty("token", s);

        return obj.toString();
    }
}
