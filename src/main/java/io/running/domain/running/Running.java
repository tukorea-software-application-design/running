package io.running.domain.running;

import io.running.domain.running.vo.Address;
import io.running.domain.running.vo.Content;
import io.running.domain.running.vo.MeetingAgeType;
import io.running.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Running {

    @Id @GeneratedValue
    @Column(name = "meeting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member owner;

    @Enumerated(EnumType.STRING)
    private MeetingAgeType meetingType;

    @Embedded
    public Address address;

    @Embedded
    private Content content;

    private boolean isOpened;

    private LocalDateTime runningDate;

    private int maxPeople;

    @OneToMany(mappedBy = "running", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RunningImage> runningImageList = new ArrayList<>();

    // TODO: 2022-12-01 생명주기가 완전 동일하지 않다면 연관관계 편의 메서드를 manyToOne 엔티티로 옮기기

    @OneToMany(mappedBy = "running", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RunningMember> runningMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "running", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RunningPost> runningPostList = new ArrayList<>();

    // TODO: 2022-11-30 문자열 runningDate 를 LocalDateTime 으로 파싱 작업
    public Running(Member owner, MeetingAgeType meetingType, Address address, Content content, int maxPeople, RunningImage... runningImages) {
        setOwner(owner);
        addRunningImage(runningImages);
        this.owner = owner;
        this.meetingType = meetingType;
        this.address = address;
        this.content = content;
        this.isOpened = true;
        this.maxPeople = maxPeople;
    }

    public void setOwner(Member owner) {
        if (this.owner != null) {
            this.owner.getRunningList().remove(this);
        }
        this.owner = owner;
        owner.getRunningList().add(this);
    }

    private void addRunningImage(RunningImage... runningImages) {
        for (RunningImage runningImage : runningImages) {
            this.runningImageList.add(runningImage);
            runningImage.setRunning(this);
        }
    }

    //cascade 가 remove 일 경우에만 같이 삭제 되므로 따로 save 호출해줘야 함
    public void addRunningMember(RunningMember runningMember) {
        this.runningMemberList.add(runningMember);
        runningMember.setRunning(this);
    }

    public void addRunningPost(RunningPost runningPost) {
        this.runningPostList.add(runningPost);
        runningPost.setRunning(this);
    }

}
