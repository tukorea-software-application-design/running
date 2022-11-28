package io.running.domain.meeting.entity;

import io.running.domain.base.BaseTimeEntity;
import io.running.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Setter
@Builder
public class MeetingPostImage extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "meeting_post_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_post_id")
    private MeetingPost meetingPost;

    private String imgUrl;
}
