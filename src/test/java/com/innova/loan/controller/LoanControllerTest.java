package com.innova.loan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.innova.loan.dto.LoanDto;
import com.innova.loan.service.LoanService;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(MockitoExtension.class)
public class LoanControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(loanController)
                .build();

    }

    @Test
    @Order(1)
    void createLoanTest() throws Exception {

        List<LoanDto> expectedLoans = getTestLoans();

        LoanDto expectedLoan = new LoanDto();
        expectedLoan.setCustomerId(1);
        expectedLoans.add(expectedLoan);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedCustomerJsonStr = ow.writeValueAsString(expectedLoan);

        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/loan")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedCustomerJsonStr))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Mockito.verify(loanService, Mockito.times(1)).createLoan(any());
    }

    @Test
    @Order(2)
    void getLoansOfCustomerTest() throws Exception {

        List<LoanDto> expectedLoans = getTestLoans();

        Mockito.when(loanService.getLoansOfCustomer(1)).thenReturn(expectedLoans);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/loan/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String responseBody = response.getContentAsString();
        JSONArray result = new JSONArray(responseBody);
        assertEquals(expectedLoans.size(), result.length());

    }

    private List<LoanDto> getTestLoans() {
        List<LoanDto> list = new ArrayList<>();
        list.add(new LoanDto(1L, 1, true, 20000D));
        list.add(new LoanDto(2L, 1, false, 1D));
        return list;
    }

}