package com.maktab.Part3MaktabFinalProject.entity;

import com.maktab.Part3MaktabFinalProject.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Wallet extends BaseEntity<Long> {
    private Long amount;

    public Wallet(Long aLong, Long amount) {
        super(aLong);
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + getId() +
                ",amount=" + amount +
                '}';
    }
}
