package com.maktab.Part3MaktabFinalProject.entity;

import com.maktab.Part3MaktabFinalProject.entity.base.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Customer extends Users {
    @OneToOne
    private Wallet wallet;
    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private List<Comment> comments;


    public Customer(String firstname, String lastname, String email, String username, String password, UserStatus userStatus, String signUpTime, Wallet wallet, List<Comment> comments) {
        super(firstname, lastname, email, username, password, userStatus, signUpTime);
        this.wallet = wallet;
        this.comments = comments;
    }

    public Customer(String firstname, String lastname, String email, String username, String password,Wallet wallet) {
        super(firstname, lastname, email, username, password);
        this.wallet = wallet;
    }

    public Customer(String username, String password) {
        super(username, password);
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id='" + getId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", firstname='" + getFirstname() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", status='" + this.getUserStatus() + '\'' +
                ", signUptime='" + getSignUpTime() + '\'' +
                ", wallet=" + wallet +
                ", comment=" + comments +
//                ", orders=" + orders +
                '}';
    }
}
