package com.maktab.Part3MaktabFinalProject.services;


import com.maktab.Part3MaktabFinalProject.entity.Experts;
import com.maktab.Part3MaktabFinalProject.repository.ExpertsRepository;
import com.maktab.Part3MaktabFinalProject.services.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpertsService implements BaseService<Experts, Long> {
    private final ExpertsRepository expertsRepository;

    public ExpertsService(ExpertsRepository expertsRepository) {
        this.expertsRepository = expertsRepository;
    }

    @Override
    @Transactional
    public Experts saveOrUpdate(Experts experts) {
        return expertsRepository.save(experts);
    }

    @Override
    @Transactional
    public List<Experts> findAll() {
        return expertsRepository.findAll();
    }

    @Override
    @Transactional
    public Experts findById(Long id) {
        return expertsRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        expertsRepository.deleteById(id);
    }

    @Transactional
    public Experts login(String username, String password) {
        return expertsRepository.findExpertsByUsernameAndPassword(username, password);
    }

    @Transactional
    public List<Experts> gridSearch(String firstName,
                                    String lastName,
                                    String email,
                                    String username) {
        return expertsRepository.findExpertsByFirstnameOrLastnameOrEmailOrUsername(firstName, lastName, email, username);
    }
    @Transactional
    public byte[] getImage(String imageLink) {
        return ExpertsRepository.getImage(imageLink);
    }
}
