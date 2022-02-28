package com.innova.loan.service;

import com.innova.loan.dto.CustomerDto;
import com.innova.loan.dto.LoanDto;
import com.innova.loan.entity.LoanEntity;
import com.innova.loan.exception.ResourceNotFoundException;
import com.innova.loan.repository.ILoanRepository;
import com.innova.loan.utils.CreditScoreUtil;
import com.innova.loan.utils.RestService;
import com.innova.loan.utils.SMSUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Loan Service
 */
@Log4j2
@Service
public class LoanService {

    @Autowired
    private ILoanRepository loanRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CreditScoreUtil creditScoreUtil;

    @Autowired
    private RestService restService;

    @Autowired
    private SMSUtil smsUtil;

    /**
     *
     * @param customerId is Customer Project's unique id
     * @return List of all loan applications
     */
    public List<LoanDto> getLoansOfCustomer(Integer customerId) {
        try {
            List<LoanEntity> list = loanRepository.findByCustomerId(customerId);
            List<LoanDto> dtoList = list.stream().map(loanEntity -> modelMapper.map(loanEntity, LoanDto.class)).collect(Collectors.toList());
            log.info("Loan Applications for Customer " + customerId + " had Gotten");
            return dtoList;
        }catch (Exception e){
            log.error("Customer Entity Does Not Exist with this id: " + customerId);
            throw new ResourceNotFoundException("Customer Entity Does Not Exist with this id: " + customerId);
        }
    }

    /**
     *
     * @param dto for making a new application. customerId is going to be enough
     * @return created application
     */
    public LoanDto createLoan(LoanDto dto) {
        try {
            CustomerDto customer = restService.getPostsPlainJSON(dto.getCustomerId());
            creditScoreUtil.approvalAndLimit(dto.getCustomerId(), dto, customer);
            smsUtil.sendSms(customer.getPhoneNumber(), dto.getApproval(), dto.getCreditLimit());
            log.info("Entity Created");
            return modelMapper.map(loanRepository.save(modelMapper.map(dto, LoanEntity.class)), LoanDto.class);

        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResourceNotFoundException("Entity Could Not Created!");

        }

    }

}
