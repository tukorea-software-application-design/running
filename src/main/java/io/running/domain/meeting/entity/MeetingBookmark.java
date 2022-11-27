package io.running.domain.meeting.entity;

import io.running.domain.base.BaseTimeEntity;
import io.running.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingBookmark extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "meeting_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
