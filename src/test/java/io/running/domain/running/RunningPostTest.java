package io.running.domain.running;

import io.running.domain.member.Member;
import io.running.domain.running.vo.Address;
import io.running.domain.running.vo.Content;
import io.running.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RunningPostTest {

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
    public void 런닝_게시글_생성() throws Exception {
        //given
        RunningPostImage runningPostImage1 = new RunningPostImage(member, "abcd");
        RunningPostImage runningPostImage2 = new RunningPostImage(member, "abcd");
        RunningPostImage runningPostImage3 = new RunningPostImage(member, "abcd");

        //when
        runningPost = new RunningPost(member, content, runningPostImage1, runningPostImage2, runningPostImage3);

        //then
        assertThat(runningPost.getRunningPostImageList().size()).isEqualTo(3);
        assertThat(runningPostImage1.getRunningPost()).isEqualTo(runningPost);
        assertThat(runningPostImage2.getRunningPost()).isEqualTo(runningPost);
        assertThat(runningPostImage3.getRunningPost()).isEqualTo(runningPost);
    }

    @Test
    public void 런닝_게시글_좋아요_생성() throws Exception {
        //given
        RunningPostLike runningPostLike = new RunningPostLike(member);

        //when
        runningPostLike.setRunningPost(runningPost);

        //then
        assertThat(runningPost.getRunningPostLikeList().size()).isEqualTo(1);
        assertThat(runningPost.getRunningPostLikeList().get(0)).isEqualTo(runningPostLike);
    }

    @Test
    public void 런닝_게시글_좋아요() throws Exception {
        //given
        RunningPostLike runningPostLike = new RunningPostLike(member);
        runningPostLike.setRunningPost(runningPost);

        //when then
        runningPostLike.changeLiked();
        assertThat(runningPostLike.isLiked()).isFalse();
        runningPostLike.changeLiked();
        assertThat(runningPostLike.isLiked()).isTrue();
    }

    @Test
    public void 런닝_게시글_중복_좋아요() throws Exception {
        //given
        RunningPostLike runningPostLike1 = new RunningPostLike(member);
        RunningPostLike runningPostLike2 = new RunningPostLike(member);

        //when then
        runningPostLike1.setRunningPost(runningPost);
        assertThrows(CustomException.class, () -> runningPostLike2.setRunningPost(runningPost));
    }

}