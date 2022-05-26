package com.maktab.Part3MaktabFinalProject.services;


import com.maktab.Part3MaktabFinalProject.entity.Wallet;
import com.maktab.Part3MaktabFinalProject.repository.WalletRepository;
import com.maktab.Part3MaktabFinalProject.services.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WalletService implements BaseService<Wallet, Long> {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet saveOrUpdate(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet findById(Long id) {
        return walletRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        walletRepository.deleteById(id);
    }
}
