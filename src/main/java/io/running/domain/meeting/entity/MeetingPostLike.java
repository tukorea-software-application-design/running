package io.running.domain.meeting.entity;

import io.running.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingPostLike {

    @Id @GeneratedValue
    @Column(name = "meeting_post_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_post_id")
    private MeetingPost meetingPost;
}
