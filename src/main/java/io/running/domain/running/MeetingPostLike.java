package io.running.domain.running;

import io.running.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MeetingPostLike {

    @Id @GeneratedValue
    @Column(name = "meeting_post_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_post_id")
    private RunningPost runningPost;
}
