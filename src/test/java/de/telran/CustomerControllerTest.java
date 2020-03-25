package de.telran;

import de.telran.config.TestConfig;
import de.telran.controller.CustomerController;
import de.telran.entity.Customer;
import de.telran.entity.Shipment;
import de.telran.service.TrackingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestConfig.class)
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TrackingService service;

    @Test
    public void testAddCustomer() throws Exception {
        Customer customerEntity = new Customer(
                null,
                "Ivan Petrov",
                null);
        Customer savedCustomerEntity = new Customer(
                1L,
                "Ivan Petrov",
                null);
        when(service.addCustomer(customerEntity)).thenReturn(savedCustomerEntity);

        mvc.perform(post("/api/customers")
                .content("{\"name\": \"Ivan Petrov\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.customerId").value("1"))
                .andExpect(jsonPath("$.name").value("Ivan Petrov"));

        verify(service, times(1)).addCustomer(customerEntity);
    }


}
