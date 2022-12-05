package io.running.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private final String key = "asdf";

    //jwt 토큰 생성
    public String createJwt(String param1) {//payload에 넣을 파라미터
        //자신이 넣고자 하는 파라미터의 수에 따라 payload의 값은 변경된다.

        //Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");
        //헤더에는 jwt의 암호화 방법 정보가 들어있다.

        //payload 부분 설정
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("param1", param1);
        //실제적인 jwt의 데이터를 담당하는 부분이다.

        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                .signWith(SignatureAlgorithm.HS256, key.getBytes()) // HS256과 Key로 Sign
                .compact(); // 토큰 생성

        return jwt;
    }

    //jwt 토큰 검증
    public Map<String, Object> checkJwt(String jwt) throws UnsupportedEncodingException {
        Map<String, Object> claimMap = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key.getBytes("UTF-8")) // 키 설정
                    .parseClaimsJws(jwt) // jwt의 정보를 파싱해서 시그니처 값을 검증한다.
                    .getBody();

            claimMap = claims;

        } catch (ExpiredJwtException e) { // 토큰이 만료되었을 경우
            System.out.println(e);

        } catch (Exception e) { // 나머지 에러의 경우
            System.out.println(e);
        }
        return claimMap;
    }
}
