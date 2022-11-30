package io.running.domain.running.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JoinStatus {
    
    WAITING("대기중"),
    APPROVED("승인됨"),
    REJECTED("거절됨");
    
    private String status;
}
