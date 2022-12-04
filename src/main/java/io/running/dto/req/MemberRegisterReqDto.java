package io.running.dto.req;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberRegisterReqDto {
    private String uid;

    private String name;

    private String nickname;

    private String imgUrl;

    private String introduce;

    private String email;
}
