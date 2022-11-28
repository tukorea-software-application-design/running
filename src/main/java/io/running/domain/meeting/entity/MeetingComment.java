package io.running.domain.meeting.entity;

import io.running.domain.base.BaseTimeEntity;
import io.running.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Setter
@Builder
public class MeetingComment extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "meeting_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_post_id")
    private MeetingPost meetingPost;

    @JoinColumn(name = "meeting_comment_parent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MeetingComment meetingCommentParent;

    @OneToMany(mappedBy = "meetingCommentParent", orphanRemoval = true)
    private List<MeetingComment> meetingCommentChild = new ArrayList<>();

    private String content;

}