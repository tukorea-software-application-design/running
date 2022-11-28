package io.running.domain.meeting.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MeetingAgeType {
    TEENAGE("TEENAGE"),
    TWENTIES("TWENTIES"),
    THIRTIES("THIRTIES"),
    FORTY("FORTY"),
    ANYONE("ANYONE");

    private String detail;
}
