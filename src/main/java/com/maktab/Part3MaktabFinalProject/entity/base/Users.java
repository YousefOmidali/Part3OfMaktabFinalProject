package com.maktab.Part3MaktabFinalProject.entity.base;

import com.maktab.Part3MaktabFinalProject.entity.UserStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class Users extends BaseEntity<Long> {
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    private String signUpTime;

    public Users(Long id, String firstname, String lastname, String email, String username, String password, UserStatus userStatus, String signUpTime) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userStatus = userStatus;
        this.signUpTime = signUpTime;
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Users(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
