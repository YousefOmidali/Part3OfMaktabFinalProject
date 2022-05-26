package com.maktab.Part3MaktabFinalProject.entity;

import com.maktab.Part3MaktabFinalProject.entity.base.Users;
import com.maktab.Part3MaktabFinalProject.entity.exceptions.FileIsTooBig;
import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Experts extends Users {

    private Long likes;     //This is expert's rating system
    @Lob
    @Column(name = "IMAGE")
    private Blob image;
    private String imageLink;
    @ManyToMany(mappedBy = "experts", fetch = FetchType.EAGER)
    private Set<SubService> subService = new HashSet<>();
    @OneToOne
    private Wallet wallet;


    @SneakyThrows
    public Experts(String firstname, String lastname, String email, String username, String password, UserStatus userStatus, String signUpTime, Long likes, Blob image, Set<SubService> subService, Wallet wallet) {
        super(firstname, lastname, email, username, password, userStatus, signUpTime);
        this.likes = likes;
        if ((image.length() / 1024) <= 300)
            this.image = image;
        else
            throw new FileIsTooBig("file is too big! (upto 300kb & jpg)");
        this.subService = subService;
        this.wallet = wallet;
    }

    public Experts(String username, String password) {
        super(username, password);
    }

    public String toString() {
        return "Expert{" +
                "id='" + getId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", firstname='" + getFirstname() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", status='" + this.getUserStatus() + '\'' +
                ", signUptime='" + getSignUpTime() + '\'' +
                ", subService=" + getSubService() + '\'' +
                ", likes=" + getLikes() + '\'' +
                ", image=" + getImage() + '\'' +
                '}';
    }
}
