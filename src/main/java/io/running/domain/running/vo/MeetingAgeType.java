package io.running.domain.running.vo;

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
