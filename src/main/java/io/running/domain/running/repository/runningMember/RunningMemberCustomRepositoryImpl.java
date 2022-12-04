package io.running.domain.running.repository.runningMember;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.running.domain.running.QRunningMember;
import io.running.domain.running.RunningMember;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;

import static io.running.domain.running.QRunningMember.runningMember;

@AllArgsConstructor
public class RunningMemberCustomRepositoryImpl implements RunningMemberCustomRepository{

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public Long deleteByMeetingIdAndMemberId(Long runningId, Long memberId) {
        long execute = queryFactory
                .delete(runningMember)
                .where(runningMember.running.id.eq(runningId),
                        runningMember.member.id.eq(memberId))
                .execute();
        em.flush();
        em.clear();
        return execute;
    }

    @Override
    public RunningMember findRunningMemberByRunningIdAndMemberID(Long runningId, Long memberId) {
        return queryFactory
                .selectFrom(QRunningMember.runningMember)
                .where(QRunningMember.runningMember.running.id.eq(runningId),
                        QRunningMember.runningMember.member.id.eq(memberId))
                .fetchOne();
    }
}
