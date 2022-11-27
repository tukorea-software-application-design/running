package io.running.domain.member.service;

import io.running.contorller.member.dto.resp.MemberLocalRegisterRespDto;
import io.running.domain.member.entity.Member;
import io.running.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberLocalRegisterRespDto resister(MemberResisterDto memberResisterDto) {
        Member member = memberRepository.save(makeMember(memberResisterDto));
        return new MemberLocalRegisterRespDto(member);
    }

    private Member makeMember(MemberResisterDto memberResisterDto) {
        return Member.builder()
                .uid(memberResisterDto.getUid())
                .email(memberResisterDto.getEmail())
                .name(memberResisterDto.getName())
                .nickname(memberResisterDto.getNickname())
                .imgUrl(memberResisterDto.getImgUrl())
                .introduce(memberResisterDto.getIntroduce())
                .build();
    }
}
