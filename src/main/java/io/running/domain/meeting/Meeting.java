package io.running.domain.meeting;

import io.running.domain.meeting.vo.MeetingAgeType;
import io.running.domain.meeting.vo.Sex;
import io.running.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Meeting {

    @Id
    @GeneratedValue
    @Column(name = "meeting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String doName;

    private String sigungu;

    private String location;

    private String conditions;

    private Integer maxPeople;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private MeetingAgeType meetingType;

    private String period;

    private String title;

    private String content;

    private Boolean isOpened;

}
