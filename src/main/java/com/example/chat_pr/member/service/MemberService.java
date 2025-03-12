package com.example.chat_pr.member.service;

import com.example.chat_pr.member.domain.Member;
import com.example.chat_pr.member.dto.MemberListResDto;
import com.example.chat_pr.member.dto.MemberLoginReqDto;
import com.example.chat_pr.member.dto.MemberSaveReqDto;
import com.example.chat_pr.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member create(MemberSaveReqDto memberSaveReqDto){
        if(memberRepository.findByEmail(memberSaveReqDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Member newMember = Member.builder()
                .name(memberSaveReqDto.getName())
                .email(memberSaveReqDto.getEmail())
                .password(passwordEncoder.encode(memberSaveReqDto.getPassword()))
                .build();

        return memberRepository.save(newMember);
    }


    public Member login(MemberLoginReqDto memberLoginReqDto){
        return memberRepository.findByEmail(memberLoginReqDto.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 이메일입니다."));
    }

    public List<MemberListResDto> findAll(){
        List<Member> members = memberRepository.findAll();
        List<MemberListResDto> memberListResDtos = new ArrayList<>();
        for (Member m : members){
            MemberListResDto memberListResDto = new MemberListResDto();
            memberListResDto.setId(m.getId());
            memberListResDto.setEmail(m.getEmail());
            memberListResDto.setName(m.getName());
            memberListResDtos.add(memberListResDto);
        }
        return memberListResDtos;
    }


}
