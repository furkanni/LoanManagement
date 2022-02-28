package com.innova.loan.controller;

import com.innova.loan.dto.LoanDto;
import com.innova.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class has been created for API's CRUD operations of LoanEntity
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class LoanController {

    @Autowired
    private LoanService loanService;

    /**
     * @param id is the unique id of the Customer
     * @return Customer Entity List by id value
     */
    @GetMapping("/loan/{id}")
    public ResponseEntity<List<LoanDto>> getLoansOfCustomer(@PathVariable Integer id) {
        List customerDto = loanService.getLoansOfCustomer(id);
        return ResponseEntity.ok(customerDto);
    }


    /**
     * @param loanDto dto service
     * @return created a new dto
     */
    @PostMapping("/loan")
    public LoanDto createLoan(@RequestBody LoanDto loanDto) {
        loanService.createLoan(loanDto);
        return loanDto;
    }
}
