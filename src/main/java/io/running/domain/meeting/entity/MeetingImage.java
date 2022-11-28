package io.running.domain.meeting.entity;

import io.running.domain.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Setter
@Builder
public class MeetingImage extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "meeting_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    private String imgUrl;
}
