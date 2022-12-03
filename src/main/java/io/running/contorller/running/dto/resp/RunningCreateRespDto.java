package io.running.contorller.running.dto.resp;

import io.running.domain.running.Running;
import io.running.domain.running.RunningImage;
import io.running.domain.running.vo.RunningAgeType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RunningCreateRespDto {

    private Long id;

    private String title;

    private String content;

    private String doName;

    private String sigungu;

    private String location;

    private LocalDateTime runningDate;

    private RunningAgeType runningAgeType;

    private Integer maxPeople;

    private List<String> imgUrlList = new ArrayList<>();

    public RunningCreateRespDto(Running running) {
        this.id = running.getId();
        this.title = running.getContent().getTitle();
        this.content = running.getContent().getContent();
        this.doName = running.getAddress().getDoName();
        this.sigungu = running.getAddress().getDoName();
        this.location =running.getAddress().getLocation();
        this.runningDate = running.getRunningDate();
        this.runningAgeType = running.getMeetingType();
        this.maxPeople = running.getMaxPeople();
        for (RunningImage runningImage : running.getRunningImageList()) {
            imgUrlList.add(runningImage.getImgUrl());
        }
    }
}
