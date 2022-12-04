package io.running.service;

import io.running.contorller.running.dto.req.RunningCreateReqDto;
import io.running.contorller.running.dto.resp.RunningCreateRespDto;
import io.running.contorller.running.dto.resp.RunningRetrieveRespDto;
import io.running.domain.member.Member;
import io.running.domain.running.Running;
import io.running.domain.running.RunningImage;
import io.running.domain.running.RunningMember;
import io.running.domain.running.repository.runningMember.RunningMemberRepository;
import io.running.domain.running.repository.RunningRepository;
import io.running.domain.running.vo.Address;
import io.running.domain.running.vo.Content;
import io.running.exception.CustomException;
import io.running.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RunningService {

    private final RunningRepository runningRepository;
    private final RunningMemberRepository runningMemberRepository;

    public RunningCreateRespDto createRunning(Member member, RunningCreateReqDto createReqDto) {
        Running saveRunning = runningRepository.save(makeRunningBy(member, createReqDto));
        return new RunningCreateRespDto(saveRunning);
    }

    @Transactional
    public RunningRetrieveRespDto retrieveRunning(Long runningId, String header) {
        Running running = findRunningBy(runningId);
        //TODO: 러닝 모임 가입부터 만들고 조회 만들자
        return new RunningRetrieveRespDto(running);
    }

    @Transactional
    public void joinRunningRequest(Member member, Long runningId) {
        Running running = findRunningBy(runningId);
        runningMemberRepository.save(makeRunningMemberBy(member, running));
    }

    @Transactional
    public void approve(Member owner, Long runningId, Long joinRequestMemberId) {
        RunningMember runningMember = findRunningMemberBy(runningId, joinRequestMemberId);
        // @TODO: 검증 로직 추가 필요
        runningMember.approveJoinStatus();
    }

    @Transactional
    public void decline(Member owner, Long runningId, Long joinRequestMemberId) {
        Running running = findRunningBy(runningId);
        RunningMember runningMember = findRunningMemberBy(runningId, joinRequestMemberId);
        // @TODO: 검증 로직 추가 필요
        runningMember.rejectJoinStatus();
    }

    @Transactional
    public void cancelJoinRequest(Long runningId, Member member) {
        runningMemberRepository.deleteByMeetingIdAndMemberId(findRunningBy(runningId).getId(), member.getId());
    }

    private RunningMember findRunningMemberBy(Long runningId, Long joinRequestMemberId) {
        return runningMemberRepository.findRunningMemberByRunningIdAndMemberID(runningId, joinRequestMemberId);
    }

    private RunningMember makeRunningMemberBy(Member member, Running running) {
        RunningMember runningMember = new RunningMember(member);
        runningMember.setRunning(running);
        return runningMember;
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

    private Running findRunningBy(Long runningId) {
        return runningRepository.findById(runningId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RUNNING, "존재하지 않는 모임입니다."));
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
