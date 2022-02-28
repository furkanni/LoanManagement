package com.innova.loan.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Credit Score Entity
 */

@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CreditScore")
public class CreditScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "score")
    private Integer score;

    public CreditScoreEntity(Long id, Integer customerId, Integer score) {
        this.id = id;
        this.customerId = customerId;
        this.score = score;
    }
}
