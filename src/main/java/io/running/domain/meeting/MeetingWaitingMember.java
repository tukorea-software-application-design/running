package io.running.domain.meeting;

import io.running.domain.base.BaseTimeEntity;
import io.running.domain.meeting.vo.JoinRequestStatus;
import io.running.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class MeetingWaitingMember extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "meeting_waiting_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @Enumerated(EnumType.STRING)
    private JoinRequestStatus joinRequestStatus;
}
