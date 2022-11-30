package io.running.service;

import io.running.dto.req.MemberLocalRegisterReqDto;
import lombok.Getter;

@Getter
public class MemberResisterDto {

    private String uid;

    private String name;

    private String email;

    private String nickname;

    private String imgUrl;

    private String introduce;


    public MemberResisterDto(MemberLocalRegisterReqDto memberLocalRegisterReqDto) {
        this.uid = memberLocalRegisterReqDto.getUid();
        this.name = memberLocalRegisterReqDto.getName();
        this.email = memberLocalRegisterReqDto.getEmail();
        this.nickname = memberLocalRegisterReqDto.getNickname();
        this.imgUrl = memberLocalRegisterReqDto.getImgUrl();
        this.introduce = memberLocalRegisterReqDto.getIntroduce();
    }
}
