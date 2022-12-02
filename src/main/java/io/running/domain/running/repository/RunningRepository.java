package io.running.domain.running.repository;

import io.running.domain.running.Running;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunningRepository extends JpaRepository<Running, Long> {
}