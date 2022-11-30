package io.running.domain.running;

import io.running.domain.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Setter
@Builder
public class RunnigImage extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "meeting_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Running running;

    private String imgUrl;

    public RunnigImage(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
