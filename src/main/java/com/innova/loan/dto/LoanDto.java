package com.innova.loan.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LOAN DTO SERVICE
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {

    private Long id;
    private Integer customerId;
    private Boolean approval;
    private Double creditLimit;
}
