package io.running.domain.running;

import io.running.domain.running.vo.Address;
import io.running.domain.running.vo.Content;
import io.running.domain.running.vo.RunningAgeType;
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
    private RunningAgeType meetingType;

    @Embedded
    private Address address;

    @Embedded
    private Content content;

    private boolean isOpened;

    private LocalDateTime runningDate;

    private int maxPeople;

    @OneToMany(mappedBy = "running", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RunningImage> runningImageList = new ArrayList<>();

    @OneToMany(mappedBy = "running", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RunningMember> runningMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "running", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RunningPost> runningPostList = new ArrayList<>();

    // TODO: 2022-11-30 문자열 runningDate 를 LocalDateTime 으로 파싱 작업
    public Running(Member owner, RunningAgeType meetingType, Address address, Content content, int maxPeople, List<RunningImage> runningImages) {
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

    private void addRunningImage(List<RunningImage> runningImages) {
        for (RunningImage runningImage : runningImages) {
            this.runningImageList.add(runningImage);
            runningImage.setRunning(this);
        }
    }
}
