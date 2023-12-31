package com.trading.application.customer.controller;

import com.trading.application.customer.entity.Customer;
import com.trading.application.customer.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * The type Customer controller test.
 */
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    /**
     * The Customer service.
     */
    @Mock
    private CustomerService customerService;

    /**
     * The Token.
     */
    String token = "abc";

    @Mock
    private HttpServletRequest request;

    @Value("${test.jwt.token}")
    private String jwtToken;

    @BeforeEach
    public void setup() {
        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwtToken);
    }

    /**
     * Should create customer.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldCreateCustomer() throws Exception {

        Customer customer = new Customer();

        Map<String, Object> customerResponseBody = new HashMap<>();
        customerResponseBody.put("customerData", customer);
        customerResponseBody.put("token", token);
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(customerResponseBody, HttpStatus.OK);

        when(customerService.createCustomer(customer)).thenReturn(String.valueOf(customer));

        ResponseEntity<Object> actualResponse = customerController.createCustomer(customer);

        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    /**
     * Should get customer by id.
     *
     * @throws Exception the exception
     */
    @Test
    void shouldGetCustomerById() throws Exception {
        String id = "testid";

        Customer expectedCustomer = new Customer();
        when(customerService.getCustomer(id)).thenReturn(expectedCustomer);

        ResponseEntity<Object> actualResponse = customerController.getCustomerById(id);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    /**
     * Should update customer name.
     *
     * @throws Exception the exception
     */
    @Test
    void shouldUpdateCustomerName() throws Exception {

        String name = "new_name";
        String id = "auth0_65192b7fe51e9baa2513bb99";

        Customer updatedCustomer = new Customer();
        updatedCustomer.setName(name);

        when(customerService.updateCustomerName(id, name)).thenReturn(null);

        String actualResponse = customerController.updateCustomerName(updatedCustomer);

        assertEquals(null, actualResponse);
    }

    /**
     * Should delete customer account.
     *
     * @throws Exception the exception
     */
    @Test
    void shouldDeleteCustomerAccount() throws Exception {

        String userId = "testid";
        when(customerService.deleteCustomerAccount(userId)).thenReturn("AccountDeleted");

        String actualResponse = customerController.deleteCustomerAccount(userId);

        assertEquals("AccountDeleted", actualResponse);
    }

    /**
     * Should get capital.
     *
     * @throws Exception the exception
     */
    @Test
    void shouldGetCapital() throws Exception {

        String userId = "testid";
        int expectedCapital = 12000;

        when(customerService.getCustomerCapital(userId)).thenReturn(expectedCapital);
        int actualCapital = customerController.getCapital(userId);

        assertEquals(expectedCapital, actualCapital);
    }

}