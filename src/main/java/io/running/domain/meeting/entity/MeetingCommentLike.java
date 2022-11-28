package io.running.domain.meeting.entity;

import io.running.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingCommentLike {

    @Id @GeneratedValue
    @Column(name = "meeting_comment_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_comment_id")
    private MeetingComment meetingComment;
}
