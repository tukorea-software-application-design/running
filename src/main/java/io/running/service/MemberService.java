package io.running.service;

import io.running.dto.req.MemberEditReqDto;
import io.running.dto.resp.MemberLocalRegisterRespDto;
import io.running.domain.member.Member;
import io.running.domain.member.repositroy.MemberRepository;
import io.running.exception.CustomException;
import io.running.exception.ErrorCode;
import io.running.service.dto.MemberKakaoUserInfoDto;
import io.running.service.dto.MemberResisterDto;
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

    public MemberLocalRegisterRespDto localResister(MemberResisterDto memberResisterDto) {
        Member member = memberRepository.save(makeMember(memberResisterDto));
        return new MemberLocalRegisterRespDto(member);
    }

    public void resister(MemberKakaoUserInfoDto memberKakaoUserInfoDto) {
//        validateAlreadyRegistered(memberKakaoUserInfoDto);
        memberRepository.save(makeMember(memberKakaoUserInfoDto));
    }

    private void validateAlreadyRegistered(MemberKakaoUserInfoDto memberKakaoUserInfoDto) {
        Optional<Member> optionalMember = memberRepository.findByUid(memberKakaoUserInfoDto.getUid());
        if (optionalMember.isPresent()) {
            throw new CustomException(ErrorCode.EXIST_MEMBER, "해당 계정으로 이미 회원가입을 했습니다.");
        }
    }

    private Member makeMember(MemberKakaoUserInfoDto memberKakaoUserInfoDto) {
        return Member.builder()
                .uid(memberKakaoUserInfoDto.getUid())
                .name(memberKakaoUserInfoDto.getName())
                .imgUrl(memberKakaoUserInfoDto.getImgUrl())
                .email(memberKakaoUserInfoDto.getEmail())
                .build();
    }

    private Member makeMember(MemberResisterDto memberResisterDto) {
        return Member.builder()
                .uid(memberResisterDto.getUid())
                .name(memberResisterDto.getName())
                .nickname(memberResisterDto.getNickname())
                .imgUrl(memberResisterDto.getImgUrl())
                .introduce(memberResisterDto.getIntroduce())
                .email(memberResisterDto.getEmail())
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
