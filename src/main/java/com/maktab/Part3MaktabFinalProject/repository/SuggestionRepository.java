package com.maktab.Part3MaktabFinalProject.repository;

import com.maktab.Part3MaktabFinalProject.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    List<Suggestion> findSuggestionsByOrder_IdOrderBySuggestedPriceDesc(Long id);
}
