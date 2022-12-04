package io.running.domain.running.repository.runningMember;

import io.running.domain.running.RunningMember;

public interface RunningMemberCustomRepository {

    Long deleteByMeetingIdAndMemberId(Long runningId, Long memberId);

    RunningMember findRunningMemberByRunningIdAndMemberID(Long runningId, Long memberId);
}
