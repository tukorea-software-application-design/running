package io.running.domain.running.repository;

import io.running.domain.running.Running;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface RunningCustomRepository {
    Slice<Running> findAllSlicingWithFetchJoinMember(Pageable pageable);
}
