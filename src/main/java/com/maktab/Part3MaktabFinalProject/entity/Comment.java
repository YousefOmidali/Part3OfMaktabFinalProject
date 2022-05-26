package com.maktab.Part3MaktabFinalProject.entity;

import com.maktab.Part3MaktabFinalProject.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Comment extends BaseEntity<Long> {
    private String description;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Order order;

}
