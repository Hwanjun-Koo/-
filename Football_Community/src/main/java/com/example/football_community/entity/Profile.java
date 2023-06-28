package com.example.football_community.entity;

import jakarta.persistence.*;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profile_id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String fullName;
    @Column
    private Integer age;
    @Column
    private String bio;
    @ManyToOne
    @JoinColumn(name = "fav_team_id", referencedColumnName = "team_id")
    private Team favTeam;

    public Long getProfile_id() {
        return profile_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if(user.getProfile() != this) {
            user.setProfile(this);
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Team getFavTeam() {
        return favTeam;
    }

    public void setFavTeam(Team favTeam) {
        if (favTeam != null && (this.favTeam != favTeam || !favTeam.getProfile().contains(this))) {
            // 이전에 연결된 팀과의 관계를 해제
            if (this.favTeam != null) {
                this.favTeam.getProfile().remove(this);
            }

            this.favTeam = favTeam;

            // 새로운 팀과의 관계를 설정
            if (!favTeam.getProfile().contains(this)) {
                favTeam.getProfile().add(this);
            }
        }
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
