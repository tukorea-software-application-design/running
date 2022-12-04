package io.running.domain.member;

import io.running.domain.base.BaseTimeEntity;
import io.running.domain.running.Running;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Lob
    private String uid;

    private String name;

    private String nickname;

    private String imgUrl;

    private String introduce;

    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Running> runningList = new ArrayList<>();

    public Member(String uid, String name, String nickname, String imgUrl, String introduce, String email) {
        this.uid = uid;
        this.name = name;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
        this.introduce = introduce;
        this.email = email;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateMemberInfo(String nickname, String introduce) {
        this.nickname = nickname;
        this.introduce = introduce;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

