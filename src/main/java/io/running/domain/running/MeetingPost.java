package io.running.domain.running;

import io.running.domain.base.BaseTimeEntity;
import io.running.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Setter
@Builder
public class MeetingPost extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "meeting_post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Running running;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String content;

}
