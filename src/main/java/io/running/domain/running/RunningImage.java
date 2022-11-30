package io.running.domain.running;

import io.running.domain.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Setter
@Builder
public class RunningImage extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "running_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "running_id")
    private Running running;

    private String imgUrl;

    public RunningImage(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
