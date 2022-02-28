package com.innova.loan.service;

import com.innova.loan.dto.LoanDto;
import com.innova.loan.repository.ILoanRepository;
import com.innova.loan.utils.CreditScoreUtil;
import com.innova.loan.utils.RestService;
import com.innova.loan.utils.SMSUtil;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private LoanService loanService;

    @Mock
    private ILoanRepository loanRepository;

    @Spy
    private SMSUtil smsUtil;

    @Spy
    private CreditScoreUtil creditScoreUtil;

    @Mock
    private RestService restService;

    @Spy
    private Environment environment;

    @Test
    @Order(1)
    void getAllTest() {
        List<LoanDto> expectedLoansInformations = Arrays.asList(
                new LoanDto(1L, 1, true, 10000D),
                new LoanDto(2L, 1, false, 0D),
                new LoanDto(1L, 1, true, 20000D)
        );

        when(loanService.getLoansOfCustomer(1)).thenReturn(expectedLoansInformations);

        assertEquals(loanService.getLoansOfCustomer(1), expectedLoansInformations);
        assertEquals(loanService.getLoansOfCustomer(1).size(), expectedLoansInformations.size());
    }

    /*@Test
    @Order(2)
    void create() {
        LoanDto dto = LoanDto.builder().customerId(1).build();
        LoanDto expectedCustomer = new LoanDto(1L, 1, true, 10000D);

        when(loanService.createLoan(dto)).thenReturn(expectedCustomer);

        loanService.createLoan(dto);

        verify(loanRepository, times(1)).save(modelMapper.map(expectedCustomer, LoanEntity.class));

    }*/

}
