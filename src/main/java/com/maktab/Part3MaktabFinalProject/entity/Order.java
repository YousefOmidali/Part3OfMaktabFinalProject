package com.maktab.Part3MaktabFinalProject.entity;

import com.maktab.Part3MaktabFinalProject.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class Order extends BaseEntity<Long> {
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private SubService subService;
    private String madeTime;
    private Long suggestedPrice;
    private String workDescription;
    private String workDate;
    private String address;
    private Boolean accepted;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
