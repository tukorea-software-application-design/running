package io.running.domain.running;

import io.running.domain.base.BaseTimeEntity;
import io.running.domain.running.vo.JoinStatus;
import io.running.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class RunningMember extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "running_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "running_id")
    private Running running;

    @Enumerated(EnumType.STRING)
    private JoinStatus joinStatus;

    public RunningMember(Member member) {
        this.member = member;
        this.joinStatus = JoinStatus.WAITING;
    }

    public void rejectJoinStatus() {
        this.joinStatus = JoinStatus.REJECTED;
    }

    public void approveJoinStatus() {
        this.joinStatus = JoinStatus.APPROVED;
    }

}
