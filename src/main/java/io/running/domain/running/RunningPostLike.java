package io.running.domain.running;

import io.running.domain.member.Member;
import io.running.exception.CustomException;
import io.running.exception.ErrorCode;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RunningPostLike {

    @Id
    @GeneratedValue
    @Column(name = "running_post_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "running_post_id")
    private RunningPost runningPost;

    public RunningPostLike(Member member) {
        this.member = member;
    }

    public void setRunningPost(RunningPost runningPost) {
        if (runningPost.isMemberRunningPostLiked(member)) {
            runningPost.removePostLikeByMember(member);
        } else {
            this.runningPost = runningPost;
            runningPost.getRunningPostLikeList().add(this);
        }

    }
}
