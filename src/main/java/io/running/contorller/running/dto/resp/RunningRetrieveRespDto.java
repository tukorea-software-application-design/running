package io.running.contorller.running.dto.resp;

import io.running.domain.member.Member;
import io.running.domain.running.Running;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RunningRetrieveRespDto {

    private Long id;

    private Long ownerId;



    public RunningRetrieveRespDto(Running running) {

    }
}
