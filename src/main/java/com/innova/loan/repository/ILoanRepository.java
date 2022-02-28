package com.innova.loan.repository;

import com.innova.loan.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository service for Loan Application
 * Also includes a unique findByCustomerId method for Loan Services
 */
@Repository
public interface ILoanRepository extends JpaRepository<LoanEntity, Long> {

    List<LoanEntity> findByCustomerId(Integer customerId);
}
