package io.running.domain.running;

import io.running.domain.running.vo.Address;
import io.running.domain.running.vo.Content;
import io.running.domain.running.vo.MeetingAgeType;
import io.running.domain.member.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RunningTest {

    @Test
    public void 미팅_생성() throws Exception {
        Member member = new Member("1234", "issiscv@naver.com", "김상운", "img.com", "나는 개똥 벌레");

        MeetingAgeType anyone = MeetingAgeType.ANYONE;
        Address meetingAddress = new Address();
        Content content = new Content("제목", "내용");

        RunnigImage runningImage1 = new RunnigImage("runnig1.png");
        RunnigImage runningImage2 = new RunnigImage("runnig2.png");
        RunnigImage runningImage3 = new RunnigImage("runnig3.png");

        Running running1 = new Running(member, anyone, meetingAddress, content, 10, runningImage1, runningImage2, runningImage3);

        assertThat(running1.getOwner().getRunningList().size()).isEqualTo(member.getRunningList().size());
        assertThat(running1.getRunningImageList().size()).isEqualTo(3);
        assertThat(runningImage1.getRunning()).isEqualTo(running1);
        assertThat(runningImage2.getRunning()).isEqualTo(running1);
        assertThat(runningImage3.getRunning()).isEqualTo(running1);
    }

}