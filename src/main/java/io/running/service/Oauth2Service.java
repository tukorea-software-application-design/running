package io.running.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class Oauth2Service {

    public String requestKakaoAccessToken(String code) throws JsonProcessingException {
        String kakaoTokenRequestUrl = "https://kauth.kakao.com/oauth/token";
    
        //헤더
        HttpHeaders headers  = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    
        //바디
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "7f8c9995a36f55554348032c83b16b96");
        body.add("redirect_uri", "http://15.164.142.62:8080/login/oauth2/code/kakao");
        body.add("code", code);

        // HttpHeader와 HttpBody를 하나의 오브젝트로 담는다
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(body, headers);//요청 바디, 헤더

        // 실제요청
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .postForEntity(kakaoTokenRequestUrl, kakaoTokenRequest, String.class);

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String token = jsonNode.get("access_token").asText();

        System.out.println("==================================================================");
        System.out.println("token = " + token);
        System.out.println("responseBody = " + responseBody);
        System.out.println("==================================================================");

        String kakaoUserInfo = getKakaoUserInfo(token);

        return kakaoUserInfo;
    }

    private String getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        String kakaoUserRequestUrl = "https://kapi.kakao.com/v2/user/me";

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .postForEntity(kakaoUserRequestUrl, kakaoUserInfoRequest, String.class);

        String responseBody = response.getBody();

        System.out.println("==================================================================");
        System.out.println("responseBody = " + responseBody);
        System.out.println("==================================================================");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();

        return id + " " + nickname;
    }

    public ResponseEntity<String> requestGoogleAccessToken(String code) {
        String googleTokenRequestUrl = "https://oauth2.googleapis.com/token";

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", "351753992442-p4p3lrp9l0jqb7neaiu3tttolvga88ng.apps.googleusercontent.com");
        params.put("client_secret", "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");
        params.put("redirect_uri", "http://localhost:8080/login/oauth2/code/google");
        params.put("grant_type", "authorization_code");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(googleTokenRequestUrl,
                params, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity;
        }
        return null;
    }


    public void createKakaoUser(String token) {

        String reqURL = "https://kapi.kakao.com/v2/user/me";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            int id = element.getAsJsonObject().get("id").getAsInt();
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String email = "";
            if(hasEmail){
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }

            System.out.println("id : " + id);
            System.out.println("email : " + email);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
