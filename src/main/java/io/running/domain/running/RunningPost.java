package io.running.domain.running;

import io.running.domain.base.BaseTimeEntity;
import io.running.domain.member.Member;
import io.running.domain.running.vo.Content;
import io.running.exception.CustomException;
import io.running.exception.ErrorCode;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RunningPost extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "running_post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "running_id")
    private Running running;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Content content;

    @OneToMany(mappedBy = "runningPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RunningPostImage> runningPostImageList = new ArrayList<>();

    @OneToMany(mappedBy = "runningPost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RunningPostLike> runningPostLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "runningPost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RunningComment> runningCommentList = new ArrayList<>();
    
    public RunningPost(Member member, Content content, RunningPostImage... runningPostImages) {
        addRunningPostImages(runningPostImages);
        this.member = member;
        this.content = content;
    }

    public void setRunning(Running running) {
        this.running = running;
        running.getRunningPostList().add(this);
    }

    public boolean isMemberRunningPostLiked(Member member) {
        for (RunningPostLike runningPostLike : runningPostLikeList) {
            if (runningPostLike.getMember() == member) {
                return true;
            }
        }
        return false;
    }

    public void removePostLikeByMember(Member member) {
        RunningPostLike tmp = null;

        for (RunningPostLike runningPostLike : runningPostLikeList) {
            if (runningPostLike.getMember() == member) {
                tmp = runningPostLike;
                break;
            }
        }

        runningPostLikeList.remove(tmp);
    }

    private void addRunningPostImages(RunningPostImage... runningPostImages) {
        for (RunningPostImage runningPostImage : runningPostImages) {
            this.runningPostImageList.add(runningPostImage);
            runningPostImage.setRunningPost(this);
        }
    }
}
