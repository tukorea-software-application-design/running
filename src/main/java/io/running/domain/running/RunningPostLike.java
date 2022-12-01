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

    private boolean isLiked;

    public RunningPostLike(Member member) {
        this.isLiked = true;
        this.member = member;
    }

    public void setRunningPost(RunningPost runningPost) {
        this.runningPost = runningPost;
        for (RunningPostLike runningPostLike : runningPost.getRunningPostLikeList()) {
            if (runningPostLike.getMember() == this.member) {
                throw new CustomException(ErrorCode.BAD_REQUEST, "이미 좋아요를 눌렀습니다.");
            }
        }

        runningPost.getRunningPostLikeList().add(this);
    }

    // TODO: 2022-12-01 좋아요 변경 시 runningPost 컬렉션 동기화
    public void changeLiked() {
        this.isLiked = this.isLiked == true ? false : true;
    }
}
