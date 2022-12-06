package io.running.contorller.running.dto.resp;

import io.running.domain.member.Member;
import io.running.domain.running.Running;
import io.running.domain.running.RunningImage;
import io.running.domain.running.RunningMember;
import io.running.dto.resp.MemberRetrieveOneMemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RunningRetrieveRespDto {

    private Long id;

    private Long ownerId;

    private String ownerNickname;

    private boolean isOpened;

    private String doName;

    private String sigungu;

    private String location;

    private String meetingType;

    private String title;

    private String content;

    private int maxPeople;

    private int joinPeople;

    private LocalDateTime runningDate;

    private List<String> imgUrl = new ArrayList<>();

    private List<MemberRetrieveOneMemberDto> joinMembers = new ArrayList<>();

    public RunningRetrieveRespDto(Running running) {
        this.id = running.getId();
        this.ownerId = running.getOwner().getId();
        this.ownerNickname = running.getOwner().getNickname();
        this.isOpened = running.isOpened();
        this.doName = running.getAddress().getDoName();
        this.sigungu = running.getAddress().getSigungu();
        this.location = running.getAddress().getLocation();
        this.meetingType = running.getMeetingType().getDetail();
        this.title = running.getContent().getTitle();
        this.content = running.getContent().getContent();
        this.maxPeople = running.getMaxPeople();
        this.joinPeople = running.getRunningMemberList().size();
        this.runningDate = running.getRunningDate();
        for (RunningImage runningImage : running.getRunningImageList()) {
            this.imgUrl.add(runningImage.getImgUrl());
        }
        for (RunningMember runningMember : running.getRunningMemberList()) {
            MemberRetrieveOneMemberDto memberRetrieveOneMemberDto = new MemberRetrieveOneMemberDto(runningMember);
            joinMembers.add(memberRetrieveOneMemberDto);
        }
    }
}
