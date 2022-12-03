package io.running.contorller.running.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RunningCreateRespDto {

    private Long meetingId;

    private String title;

    private String content;

    private String doName;

    private String sigungu;

    private String location;

    private String sex;

    private String conditions;

    private String category;

    private String meetingType;

    private String period;

    private Integer maxPeople;

    private List<String> imgUrlList = new ArrayList<>();

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

}
