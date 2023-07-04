package com.example.football_community.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private LocalDate createdDate;
    private LocalDate modifiedDate;


    public UserDTO() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = LocalDate.from(createdDate);
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = LocalDate.from(modifiedDate);
    }

    @Override
    public String toString() {
        return "{" +
                "\n\tid = " + id +
                "\n\tusername = '" + username + '\'' +
                "\n\temail = '" + email + '\'' +
                "\n\tphoneNumber = '" + phoneNumber + '\'' +
                "\n\tcreatedDate = " + createdDate +
                "\n\tmodifiedDate = " + modifiedDate +
                "\n}";
    }
}
