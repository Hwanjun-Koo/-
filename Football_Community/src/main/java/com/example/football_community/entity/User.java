package com.example.football_community.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column
    private String phoneNumber;
    @Column(nullable = false)
    private LocalDateTime createdTime;
    @Column(nullable = false)
    private LocalDateTime modifiedTime;
    @Column(nullable = false)
    private String status;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Profile profile;

    @OneToMany(mappedBy = "follower")
    private List<Follow> followers;

    @OneToMany(mappedBy = "following")
    private List<Follow> followings;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Like> likes;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<MatchReview> reviews;

    public User() {

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public LocalDateTime getCreatedDate() {
        return createdTime;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdTime = createdDate;
    }
    public LocalDateTime getModifiedDate() {
        return modifiedTime;
    }
    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedTime = modifiedDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
        if(profile.getUser() != this) {
            profile.setUser(this);
        }
    }

    public void addFollowing(Follow follow) {
        followings.add(follow);
    }

    public void removeFollowing(Follow follow) {
        followings.remove(follow);
    }

    public void addFollower(Follow follow) {
        followers.add(follow);
    }

    public void removeFollower(Follow follow) {
        followers.remove(follow);
    }

    public List<MatchReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<MatchReview> reviews) {
        this.reviews = reviews;
    }
}
