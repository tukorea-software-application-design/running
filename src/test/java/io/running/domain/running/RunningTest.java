package io.running.domain.running;

import io.running.domain.running.vo.Address;
import io.running.domain.running.vo.Content;
import io.running.domain.running.vo.JoinStatus;
import io.running.domain.running.vo.RunningAgeType;
import io.running.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class RunningTest {

    Member member;

    RunningAgeType anyone;
    Address meetingAddress;
    Content content;

    RunningImage runningImage1;
    RunningImage runningImage2;
    RunningImage runningImage3;

    Running running;

    RunningMember runningMember1;
    RunningMember runningMember2;
    RunningMember runningMember3;

    @BeforeEach
    public void beforeEach() {
        member = new Member("1234", "issiscv@naver.com", "김상운", "img.com", "나는 개똥 벌레");

        anyone = RunningAgeType.ANYONE;
        meetingAddress = new Address();
        content = new Content("제목", "내용");

        runningImage1 = new RunningImage("runnig1.png");
        runningImage2 = new RunningImage("runnig2.png");
        runningImage3 = new RunningImage("runnig3.png");

        List<RunningImage> runningImages = new ArrayList<>();

        runningImages.add(runningImage1);
        runningImages.add(runningImage2);
        runningImages.add(runningImage3);

        running = new Running(member, anyone, meetingAddress, this.content, 10, runningImages);

        runningMember1 = new RunningMember(member);
        runningMember2 = new RunningMember(member);
        runningMember3 = new RunningMember(member);

        runningMember1.setRunning(running);
        runningMember2.setRunning(running);
        runningMember3.setRunning(running);

    }

    @Test
    public void 런닝_생성() throws Exception {

        assertThat(running.getOwner().getRunningList().size()).isEqualTo(member.getRunningList().size());
        assertThat(running.getRunningImageList().size()).isEqualTo(3);
        assertThat(runningImage1.getRunning()).isEqualTo(running);
        assertThat(runningImage2.getRunning()).isEqualTo(running);
        assertThat(runningImage3.getRunning()).isEqualTo(running);

    }

    @Test
    public void 런닝_가입() throws Exception {

        assertThat(running.getRunningMemberList().size()).isEqualTo(3);
        assertThat(runningMember1.getRunning()).isEqualTo(running);
        assertThat(runningMember2.getRunning()).isEqualTo(running);
        assertThat(runningMember3.getRunning()).isEqualTo(running);
    }

    @Test
    public void 런닝_가입_승인() throws Exception {
        //when
        runningMember1.approveJoinStatus();
        runningMember2.rejectJoinStatus();

        //then
        assertThat(runningMember1.getJoinStatus()).isEqualTo(JoinStatus.APPROVED);
        assertThat(runningMember2.getJoinStatus()).isEqualTo(JoinStatus.REJECTED);
        assertThat(running.getRunningMemberList().get(0).getJoinStatus()).isEqualTo(JoinStatus.APPROVED);
        assertThat(running.getRunningMemberList().get(1).getJoinStatus()).isEqualTo(JoinStatus.REJECTED);
    }

    @Test
    public void 런닝_게시글_작성() throws Exception {
        //given
        RunningPost runningPost = new RunningPost(member, content);

        //when
        runningPost.setRunning(running);

        //then
        assertThat(running.getRunningPostList().size()).isEqualTo(1);
        assertThat(running.getRunningPostList().get(0)).isEqualTo(runningPost);
    }

}