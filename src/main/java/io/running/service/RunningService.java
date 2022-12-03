package io.running.service;

import io.running.contorller.running.dto.req.RunningCreateReqDto;
import io.running.contorller.running.dto.resp.RunningCreateRespDto;
import io.running.domain.member.Member;
import io.running.domain.running.Running;
import io.running.domain.running.RunningImage;
import io.running.domain.running.repository.RunningRepository;
import io.running.domain.running.vo.Address;
import io.running.domain.running.vo.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RunningService {

    private final RunningRepository runningRepository;

    public RunningCreateRespDto createRunning(Member member, RunningCreateReqDto createReqDto) {
        Running running = makeRunningBy(member, createReqDto);
        runningRepository.save(running);
        return null;
    }

    private Running makeRunningBy(Member member, RunningCreateReqDto createReqDto) {
        //TODO 가변인자를 List로 받아서 처리하는 방법이 있는지?
        return new Running(member,
                createReqDto.getRunningAgeType(), 
                makeAddress(createReqDto), 
                makeContent(createReqDto), 
                createReqDto.getMaxPeople(),
                makeRunningImages(createReqDto));
    }

    private List<RunningImage> makeRunningImages(RunningCreateReqDto createReqDto) {
        List<RunningImage> runningImages = new ArrayList<>();
        for (String url : createReqDto.getImgUrlList()) {
            runningImages.add(new RunningImage(url));
        } return runningImages;
    }

    private Address makeAddress(RunningCreateReqDto createReqDto) {
        Address address = Address.builder()
                .doName(createReqDto.getDoName())
                .sigungu(createReqDto.getSigungu())
                .location(createReqDto.getLocation())
                .build();
        return address;
    }

    private Content makeContent(RunningCreateReqDto createReqDto) {
        Content content = Content.builder()
                .title(createReqDto.getTitle())
                .content(createReqDto.getContent())
                .build();
        return content;
    }
}
