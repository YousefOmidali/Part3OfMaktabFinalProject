package com.maktab.Part3MaktabFinalProject.repository;

import com.maktab.Part3MaktabFinalProject.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findAdminByUsernameAndPassword(String username, String password);

    List<Admin> findAdminsByFirstnameOrLastnameOrEmailOrUsername(
            String firstname
            , String lastname
            , String email
            , String username);
    
}
