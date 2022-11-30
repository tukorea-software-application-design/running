package io.running.dto.resp;

import io.running.domain.member.Member;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MemberLocalRegisterRespDto {

    private Long id;

    private String uid;

    private String email;

    private String name;

    private String nickname;

    private String imgUrl;

    private String introduce;

    public MemberLocalRegisterRespDto(Member member) {
        this.id = member.getId();
        this.uid = member.getUid();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.imgUrl = member.getImgUrl();
        this.introduce = member.getIntroduce();
    }
}
