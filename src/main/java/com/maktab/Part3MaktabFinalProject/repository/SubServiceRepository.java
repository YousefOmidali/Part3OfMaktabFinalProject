package com.maktab.Part3MaktabFinalProject.repository;

import com.maktab.Part3MaktabFinalProject.entity.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubServiceRepository extends JpaRepository<SubService,Long> {
}
