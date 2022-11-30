package io.running.domain.running;

import io.running.domain.running.vo.Address;
import io.running.domain.running.vo.Content;
import io.running.domain.running.vo.MeetingAgeType;
import io.running.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter
@Builder
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

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RunnigImage> runningImageList = new ArrayList<>();

    public Running(Member owner, MeetingAgeType meetingType, Address address, Content content, int maxPeople, RunnigImage... runnigImages) {
        setOwner(owner);
        addRunningImage(runnigImages);
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

    public void addRunningImage(RunnigImage[] runningImages) {
        for (RunnigImage runnigImage : runningImages) {
            this.runningImageList.add(runnigImage);
            runnigImage.setRunning(this);
        }
    }
}
