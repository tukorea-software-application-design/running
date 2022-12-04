package io.running.domain.running.repository;

import io.running.domain.running.RunningMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunningMemberRepository extends JpaRepository<RunningMember, Long> {
}
