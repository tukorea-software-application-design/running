package io.running.service;

import io.running.domain.running.repository.RunningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final RunningRepository runningRepository;
}
