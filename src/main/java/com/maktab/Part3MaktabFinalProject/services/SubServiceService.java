package com.maktab.Part3MaktabFinalProject.services;


import com.maktab.Part3MaktabFinalProject.entity.SubService;
import com.maktab.Part3MaktabFinalProject.repository.SubServiceRepository;
import com.maktab.Part3MaktabFinalProject.services.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubServiceService implements BaseService<SubService, Long> {
    private final SubServiceRepository subServiceRepository;

    public SubServiceService(SubServiceRepository subServiceRepository) {
        this.subServiceRepository = subServiceRepository;
    }

    @Override
    @Transactional
    public SubService saveOrUpdate(SubService subService) {
        return subServiceRepository.save(subService);
    }

    @Override
    @Transactional
    public List<SubService> findAll() {
        return subServiceRepository.findAll();
    }

    @Override
    @Transactional
    public SubService findById(Long id) {
        return subServiceRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        subServiceRepository.deleteById(id);
    }
}
