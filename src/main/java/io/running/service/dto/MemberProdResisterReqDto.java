package io.running.service.dto;

import io.running.dto.req.MemberRegisterReqDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberProdResisterReqDto {

    private String nickname;

    private String introduce;

    public MemberProdResisterReqDto(MemberRegisterReqDto memberLocalRegisterReqDto) {
        this.nickname = memberLocalRegisterReqDto.getNickname();
        this.introduce = memberLocalRegisterReqDto.getIntroduce();
    }
}
