package com.example.football_community.domain.member.entity;

import com.example.football_community.domain.follow.Follow;
import com.example.football_community.domain.member.dto.MemberUpdateRequestDto;
import com.example.football_community.domain.post.Post;
import com.example.football_community.global.timestamp.TimeStamped;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private List<Follow> followers;
    @Column
    private int followersCount;
    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private List<Follow> followings;
    @Column
    private int followingCount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Post> posts;

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
}
