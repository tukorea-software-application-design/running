package io.running.domain.running;

import io.running.domain.member.Member;
import io.running.domain.running.vo.Address;
import io.running.domain.running.vo.Content;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RunningCommentTest {

    Member member;

    Address meetingAddress;
    Content content;

    RunningPost runningPost;

    @BeforeEach
    public void beforeEach() {
        member = new Member("1234", "issiscv@naver.com", "김상운", "img.com", "나는 개똥 벌레");

        meetingAddress = new Address();
        content = new Content("제목", "내용");

        runningPost = new RunningPost(member, content);
    }

    @Test
    public void 런닝_게시글_댓글_생성() throws Exception {
        //given
        RunningComment runningComment = new RunningComment(member, "abcd");

        //when
        runningComment.setRunningPost(runningPost);

        //then
        assertThat(runningComment.getRunningPost()).isEqualTo(runningPost);
        assertThat(runningPost.getRunningCommentList().size()).isEqualTo(1);
        assertThat(runningPost.getRunningCommentList().get(0)).isEqualTo(runningComment);
    }

    @Test
    public void 대댓글_작성() throws Exception {
        //given
        RunningComment parent = new RunningComment(member, "abcd");

        //when
        RunningComment child1 = new RunningComment(member, parent,  "abcd");
        RunningComment child2 = new RunningComment(member, parent, "abcd");

        //then
        Assertions.assertThat(parent.getRunningChildComment().size()).isEqualTo(2);
        Assertions.assertThat(parent.getRunningChildComment().get(0)).isEqualTo(child1);
        Assertions.assertThat(parent.getRunningChildComment().get(1)).isEqualTo(child2);
    }

}