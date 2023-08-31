package com.example.football_community.domain.profile;

import java.util.List;

public class ProfileDTO {
    private Long profile_id;
    private String name;

    private String gender;

    private Integer age;
    private String favTeamName;
    private String bio;
    private Integer followerCount;
    private Integer followingCount;

    public ProfileDTO() {}
    public Long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Long profile_id) {
        this.profile_id = profile_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFavTeamName() {
        return favTeamName;
    }

    public void setFavTeamName(String favTeamName) {
        this.favTeamName = favTeamName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    @Override
    public String toString() {
        return "{" +
                "\n\tid = " + profile_id +
                "\n\tname = " + name  +
                "\n\tgender = " + gender +
                "\n\tfavourite team = " + favTeamName +
                "\n\tbio = " + bio +
                "\n\tfollower = " + followerCount +
                "\n\tfollowing = " + followingCount+
                "\n}";
    }
}
