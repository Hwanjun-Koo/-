package com.example.football_community.domain.member.entity;


import com.example.football_community.domain.member.dto.request.MemberUpdateRequestDto;
import com.example.football_community.domain.post.entity.Post;
import com.example.football_community.global.timestamp.TimeStamped;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(name = "MEMBER_NAME")
    private String memberName;
    @Column(name = "NICKNAME")
    private String nickname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONENUMBER")
    private String phoneNumber;
    @Column(name = "BIRTHDAY")
    private String birthday;
    @Column(name = "AGE")
    private Integer age;

    @Column
    private String password;
    @Column
    private int followersCount;
    @Column
    private int followingCount;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts;

    // 회원 정보 수정 함수 - null값이 아닌 경우에만 새로 업데이트
    public void updateMemberInfo(MemberUpdateRequestDto requestDto){
        if (requestDto.getMemberName() != null) {
            this.memberName = requestDto.getMemberName();
        }
        if (requestDto.getNickname() != null) {
            this.nickname = requestDto.getNickname();
        }
        if (requestDto.getNickname() != null) {
            this.nickname = requestDto.getNickname();
        }
        if (requestDto.getPhoneNumber() != null) {
            this.phoneNumber = requestDto.getPhoneNumber();
        }
        if (requestDto.getBirthday() != null) {
            this.birthday = requestDto.getBirthday();
            this.calculateAge();
        }
    }

    // 6자리 birthday를 이용해 회원의 나이를 계산하는 함수
    public void calculateAge() {
        if (birthday != null) {
            LocalDate currentDate = LocalDate.now();
            int birthYear = Integer.parseInt(birthday.substring(0, 2));// YYMMDD에서 YY 추출
            int birthMonth = Integer.parseInt(birthday.substring(2, 4));  // YYMMDD에서 MM 추출
            int birthDate = Integer.parseInt(birthday.substring(4, 6));  // YYMMDD에서 DD 추출

            int currentYear = currentDate.getYear();  // 현재 년도의 YY 추출
            int currentMonth = currentDate.getMonthValue();  // 현재 월 추출
            int currentDateOfMonth = currentDate.getDayOfMonth();  // 현재 날짜 추출

            if ((currentYear % 100 < birthYear) && (birthYear < 100)){
                birthYear += 1900; // 2자리 연도를 4자리로 확장
            }
            if ((currentYear % 100 >= birthYear) && (birthYear < 100)) {
                birthYear += 2000;
            }

            int age = currentYear - birthYear;

            // 생일이 지났는지 체크
            if (birthMonth > currentMonth || (birthMonth == currentMonth && birthDate > currentDateOfMonth)) {
                age--;  // 생일이 지나지 않았다면 나이에서 1을 빼줌
            }

            this.age = age;
        }
    }

    public void addFollower(){
        this.followersCount += 1;
    }

    public void removeFollower(){
        this.followersCount -= 1;
    }

    public void addFollowing(){
        this.followingCount += 1;
    }

    public void removeFollowing() {
        this.followingCount -= 1;
    }
}
