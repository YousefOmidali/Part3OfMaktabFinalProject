package com.maktab.Part3MaktabFinalProject.entity;

import com.maktab.Part3MaktabFinalProject.entity.base.BaseEntity;
import com.maktab.Part3MaktabFinalProject.entity.exceptions.WrongPriceEntered;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Suggestion extends BaseEntity<Long> {
    private Long suggestedPrice;
    private String workTime;    // it can be one hour, one day or one month ...
    private String workStartDate;
    @ManyToOne
    private Experts experts;
    @ManyToOne
    private Order order;

    
    public Suggestion(Experts experts, Order order, Long suggestedPrice, String workTime, String workStartDate) {
        this.experts = experts;
        this.order = order;
        if (suggestedPrice >= order.getSubService().getBasePrice())
            this.suggestedPrice = suggestedPrice;
        else
            throw new WrongPriceEntered("your suggested price cant be bigger than order's base price! ");
        this.workTime = workTime;
        this.workStartDate = workStartDate;
    }
}
