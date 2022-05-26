package com.maktab.Part3MaktabFinalProject.entity;

import com.maktab.Part3MaktabFinalProject.entity.base.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Admin extends Users {


    public Admin(String firstname, String lastname, String email, String username, String password, UserStatus userStatus, String signUpTime) {
        super(firstname, lastname, email, username, password, userStatus, signUpTime);
    }

    public Admin(String username, String password) {
        super(username, password);
    }

    public String toString() {
        return "Admin{" +
                "id='" + getId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", firstname='" + getFirstname() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", status='" + this.getUserStatus() + '\'' +
                ", signUptime='" + getSignUpTime() + '\'' +
                '}';
    }
}
