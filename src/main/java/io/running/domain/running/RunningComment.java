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
public class RunningComment extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "running_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "running_post_id")
    private RunningPost runningPost;

    @JoinColumn(name = "running_comment_parent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RunningComment runningParentComment;

    @OneToMany(mappedBy = "runningCommentParent", orphanRemoval = true)
    private List<RunningComment> runningChildComment = new ArrayList<>();

    private String content;

    public RunningComment(Member member, String content) {
        this.member = member;
        this.content = content;
    }

    public RunningComment(Member member, RunningComment parentRunningComment, String content) {
        this.member = member;
        this.content = content;
        setParentComment(parentRunningComment);
    }

    private void setParentComment(RunningComment parentRunningComment) {
        this.runningParentComment = parentRunningComment;
        parentRunningComment.getRunningChildComment().add(this);
    }

    public void setRunningPost(RunningPost runningPost) {
        this.runningPost = runningPost;
        runningPost.getRunningCommentList().add(this);
    }
}
