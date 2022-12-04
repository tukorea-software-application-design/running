package io.running.service.dto;

import io.running.dto.req.MemberRegisterReqDto;
import lombok.Getter;

@Getter
public class MemberResisterDto {

    private String uid;

    private String name;

    private String nickname;

    private String imgUrl;

    private String introduce;

    private String email;


    public MemberResisterDto(MemberRegisterReqDto memberLocalRegisterReqDto) {
        this.uid = memberLocalRegisterReqDto.getUid();
        this.name = memberLocalRegisterReqDto.getName();
        this.nickname = memberLocalRegisterReqDto.getNickname();
        this.imgUrl = memberLocalRegisterReqDto.getImgUrl();
        this.introduce = memberLocalRegisterReqDto.getIntroduce();
        this.email = memberLocalRegisterReqDto.getEmail();
    }

    public MemberResisterDto(MemberProdResisterReqDto memberProdResisterReqDto, MemberKakaoUserInfoDto kakaoUserInfo) {
        this.uid = kakaoUserInfo.getUid();
        this.name = kakaoUserInfo.getName();
        this.nickname = memberProdResisterReqDto.getNickname();
        this.imgUrl = kakaoUserInfo.getImgUrl();
        this.introduce = memberProdResisterReqDto.getIntroduce();
        this.email = kakaoUserInfo.getEmail();
    }
}
