package com.innova.loan.utils;

import com.innova.loan.dto.CustomerDto;
import com.innova.loan.dto.LoanDto;
import com.innova.loan.repository.ICreditScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Necessary Calculations of Credit limits of applications with the given conditions
 * Also gives the information of application's result : True (Accepted) or False (Not Accepted)
 */
@Component
public class CreditScoreUtil {

    @Autowired
    private Environment environment;

    @Autowired
    ICreditScoreRepository creditScoreRepository;

    @Autowired
    RestService restService;

    /**
     *
     * @param customerId to get customer's information
     * @param loan to give informations's to database
     * @param customer selection of the customer
     */
    public void approvalAndLimit(Integer customerId, LoanDto loan, CustomerDto customer) {
        Integer creditScore = creditScoreRepository.findByCustomerId(customerId).get().getScore();
        Double income = 0D;
        income = customer.getIncome();

        if (creditScore < 500) {
            loan.setApproval(false);
            loan.setCreditLimit(0D);
        } else if (creditScore >= 500 && creditScore < 1000) {
            if (income <= 5000) {
                loan.setCreditLimit(10000D);
            } else {
                loan.setCreditLimit(20000D);
            }
            loan.setApproval(true);
        } else {
            loan.setCreditLimit((Integer.parseInt(environment.getProperty("loan.multiplier")) * income));
            loan.setApproval(true);
        }
    }

}
