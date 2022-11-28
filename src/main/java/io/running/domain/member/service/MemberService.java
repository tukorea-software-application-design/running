package io.running.domain.member.service;

import io.running.contorller.member.dto.req.MemberEditReqDto;
import io.running.contorller.member.dto.resp.MemberLocalRegisterRespDto;
import io.running.domain.member.entity.Member;
import io.running.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

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

    @Transactional
    public void editInfo(Member member, MemberEditReqDto memberEditReqDto) {
        member.updateMemberInfo(memberEditReqDto.getNickname(), memberEditReqDto.getIntroduce());
        memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        return findOptionalMember(uid).orElseThrow(() -> {
            throw new UsernameNotFoundException("해당 회원이 존재하지 않습니다.");
        });
    }


    private Optional<Member> findOptionalMember(String uid) {
        return memberRepository.findByUid(uid);
    }
}
