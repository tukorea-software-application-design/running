package io.running.contorller.member.dto.req;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberLocalRegisterReqDto {
    private String uid;

    private String email;

    private String name;

    private String nickname;

    private String imgUrl;

    private String introduce;
}
