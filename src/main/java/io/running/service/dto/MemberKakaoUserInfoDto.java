package io.running.service.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

@Getter
public class MemberKakaoUserInfoDto {

    private String uid;

    private String name;

    private String imgUrl;

    private String email;

    public MemberKakaoUserInfoDto(JsonNode kakaoUserInfo) {
        this.uid = kakaoUserInfo.get("id").asText();
        this.name = kakaoUserInfo.get("properties").get("nickname").asText();
        this.imgUrl = kakaoUserInfo.get("properties").get("thumbnail_image").asText();
        this.email = kakaoUserInfo.get("kakao_account").get("email").asText();
    }
}
