package com.maktab.Part3MaktabFinalProject.repository;


import com.maktab.Part3MaktabFinalProject.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
}
