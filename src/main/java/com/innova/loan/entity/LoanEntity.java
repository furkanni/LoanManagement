package com.innova.loan.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Loan Entity
 */
@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Loan")
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "approval")
    private Boolean approval;

    @Column(name = "credit_limit")
    private Double creditlimit;

    public LoanEntity(Long id, Integer customerId, Boolean approval, Double creditlimit) {
        this.id = id;
        this.customerId = customerId;
        this.approval = approval;
        this.creditlimit = creditlimit;
    }
}
