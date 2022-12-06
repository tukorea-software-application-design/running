package io.running.dto.resp;

import io.running.domain.running.RunningMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRetrieveOneMemberDto {

    private Long memberId;

    private String nickname;

    private String memberImgUrl;

    public MemberRetrieveOneMemberDto (RunningMember runningMember) {
        this.memberId = runningMember.getMember().getId();
        this.nickname = runningMember.getMember().getName();
        this.memberImgUrl = runningMember.getMember().getImgUrl();
    }
}
