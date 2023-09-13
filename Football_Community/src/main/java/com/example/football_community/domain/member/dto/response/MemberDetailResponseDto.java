package com.example.football_community.domain.member.dto.response;

import com.example.football_community.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailResponseDto {
    private Long memberId;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String birthday;
    private Integer age;
    private int followingCount;
    private int followerCount;
    //회원 가입일(TimeStamped)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    //회원 정보 수정일(TimeStamped)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    //Dto를 생성하는 함수를 포함시킴
    public static MemberDetailResponseDto of(Member member) {
        String formattedBirthday = null;
        if (member.getBirthday() != null) {
            formattedBirthday = formatDate(member.getBirthday());
        }
        return MemberDetailResponseDto.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .birthday(formattedBirthday)
                .age(member.getAge())
                .followingCount(member.getFollowingCount())
                .followerCount(member.getFollowerCount())
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .build();

    }
    //6자리의 birthday로 부터 년,월,일 순의 형식으로 포매팅하는 함수
    private static String formatDate(String birthday) {
        LocalDate currentDate = LocalDate.now();
        int birthYear = Integer.parseInt(birthday.substring(0, 2));// YYMMDD에서 YY 추출
        int birthMonth = Integer.parseInt(birthday.substring(2, 4));  // YYMMDD에서 MM 추출
        int birthDate = Integer.parseInt(birthday.substring(4, 6));  // YYMMDD에서 DD 추출

        int currentYear = currentDate.getYear();  // 현재 년도의 YY 추출

        if ((currentYear % 100 < birthYear) && (birthYear < 100)){
            birthYear += 1900; // 2자리 연도를 4자리로 확장
        }
        if ((currentYear % 100 >= birthYear) && (birthYear < 100)) {
            birthYear += 2000;
        }
        String year = Integer.toString(birthYear);
        String month = String.format("%02d", birthMonth);
        String day = String.format("%02d", birthDate);
        return year + "년 " + month + "월 " + day + "일";
    }


}
