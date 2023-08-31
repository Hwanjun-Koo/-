package com.example.football_community.domain.member;

import com.example.football_community.domain.newsfeed.Newsfeed;
import com.example.football_community.domain.profile.Profile;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public MemberDTO createUser(Member member){
        LocalDateTime now = LocalDateTime.now();
        member.setCreatedDate(now);
        member.setModifiedDate(now);
        member.setStatus("Active");
        Member savedMember = memberRepository.save(member);
        Profile profile = new Profile();
        profile.setUser(savedMember);
        profile.setName(savedMember.getUsername());
        Newsfeed newsfeed = new Newsfeed();
        newsfeed.setUser(savedMember);
        return convertToDTO(savedMember);
    }

    public MemberDTO getUser(Long userId){
        Optional<Member> userOptional = memberRepository.findById(userId);
        if(userOptional.isPresent()) {
            Member member = userOptional.get();
            return convertToDTO(member);
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }

    public List<MemberDTO> getAllUser()
    {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public MemberDTO updateUser(Long userId, MemberDTO userDetails){
        Optional<Member> userOptional = memberRepository.findById(userId);
        if(userOptional.isPresent()) {
            Member member = userOptional.get();

            if(userDetails.getUsername() != null) {
                member.setUsername(userDetails.getUsername());
            }
            if(userDetails.getEmail() != null) {
                member.setEmail(userDetails.getEmail());
            }
            if(userDetails.getPhoneNumber() != null) {
                member.setPhoneNumber(userDetails.getPhoneNumber());
            }

            member.setModifiedDate(LocalDateTime.now());
            Member updatedMember = memberRepository.save(member);
            return convertToDTO(updatedMember);
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }

    public void deleteUser(Long userId){
        Optional<Member> userOptional = memberRepository.findById(userId);
        if(userOptional.isPresent()) {
            Member member = userOptional.get();
            member.setModifiedDate(LocalDateTime.now());
            member.setStatus("Deleted");
            memberRepository.delete(member);
        } else {
            throw new RuntimeException("유저를 찾을 수 없습니다.");
        }
    }

    private MemberDTO convertToDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setUsername(member.getUsername());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setPhoneNumber(member.getPhoneNumber());
        memberDTO.setCreatedDate(member.getCreatedDate());
        memberDTO.setModifiedDate(member.getModifiedDate());
        return memberDTO;
    }

}
