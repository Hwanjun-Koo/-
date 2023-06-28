package com.example.football_community.dto;

public class ProfileDTO {
    private Long profile_id;
    private String fullName;
    private Integer age;
    private String favTeamName;
    private String bio;

    public ProfileDTO() {}
    public Long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Long profile_id) {
        this.profile_id = profile_id;
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
}
