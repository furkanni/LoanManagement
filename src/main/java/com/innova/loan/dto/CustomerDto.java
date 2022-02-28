package com.innova.loan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * CUSTOMER DTO SERVICE
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable {

    @JsonIgnore
    private final long serialVersionUID=-635146514;

    private Long id;
    private String name;
    private String surname;
    private Long identityNumber;
    private String phoneNumber;
    private Double income;
}
