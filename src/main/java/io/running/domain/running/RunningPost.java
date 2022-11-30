package io.running.domain.running;

import io.running.domain.base.BaseTimeEntity;
import io.running.domain.member.Member;
import io.running.domain.running.vo.Content;
import lombok.*;

import javax.persistence.*;

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

    private Content content;

    public RunningPost(Member member, Content content) {
        this.member = member;
        this.content = content;
    }

    public void setRunning(Running running) {
        this.running = running;
    }
}
