package io.running.domain.running;

import io.running.domain.base.BaseTimeEntity;
import io.running.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MeetingComment extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "meeting_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_post_id")
    private RunningPost runningPost;

    @JoinColumn(name = "meeting_comment_parent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MeetingComment meetingCommentParent;

    @OneToMany(mappedBy = "meetingCommentParent", orphanRemoval = true)
    private List<MeetingComment> meetingCommentChild = new ArrayList<>();

    private String content;

}
