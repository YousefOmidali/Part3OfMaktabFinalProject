package com.maktab.Part3MaktabFinalProject.repository;

import com.maktab.Part3MaktabFinalProject.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
