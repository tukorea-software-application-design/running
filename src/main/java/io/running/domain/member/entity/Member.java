package io.running.domain.member.entity;

import io.running.domain.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    //uid
    @Lob
    private String uid;

    private String email;

    private String name;

    private String nickname;

    private String imgUrl;

    private String introduce;

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

}

