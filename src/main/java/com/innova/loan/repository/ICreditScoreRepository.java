package com.innova.loan.repository;

import com.innova.loan.entity.CreditScoreEntity;
import com.innova.loan.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * Repository service for connection of Databases
 * Also includes a unique find method for CreditScoreUtil
 */
@Repository
public interface ICreditScoreRepository extends JpaRepository<CreditScoreEntity, Long> {

    Optional<CreditScoreEntity> findByCustomerId(Integer customerId);
}
