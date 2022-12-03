package io.running.contorller.running.dto.req;

import io.running.domain.running.vo.RunningAgeType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RunningCreateReqDto {

    private String title;

    private String content;

    private String doName;

    private String sigungu;

    private String location;

    private LocalDateTime runningDate;

    private RunningAgeType runningAgeType;

    private Integer maxPeople;

    private List<String> imgUrlList = new ArrayList<>();

}
