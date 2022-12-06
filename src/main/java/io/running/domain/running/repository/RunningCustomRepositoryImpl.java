package io.running.domain.running.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.running.domain.member.QMember;
import io.running.domain.running.QRunning;
import io.running.domain.running.Running;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static io.running.domain.member.QMember.member;
import static io.running.domain.running.QRunning.*;

@RequiredArgsConstructor
public class RunningCustomRepositoryImpl implements RunningCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<Running> findAllSlicingWithFetchJoinMember(Pageable pageable) {
        List<Running> content = queryFactory
                .selectFrom(running)
                .join(running.owner, member).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .orderBy(running.id.desc())
                .fetch();

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }
}
