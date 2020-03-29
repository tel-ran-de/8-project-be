package de.telran;

import de.telran.config.TestConfig;
import de.telran.controller.CustomerController;
import de.telran.entity.Customer;
import de.telran.entity.Shipment;
import de.telran.entity.Tracking;
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

    @Test
    public void testGetAllCustomersWithShipmentsAndTrackings() throws Exception {
        when(service.getAllCustomers())
                .thenReturn(createListOfCustomers());

        mvc.perform(get("/api/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].name").value("Ivan"))
                .andExpect(jsonPath("$[0].shipments").exists())
                .andExpect(jsonPath("$[0].customerId").value("7"))
                .andExpect(jsonPath("$[1].name").value("Petr"))
                .andExpect(jsonPath("$[1].shipments").exists())
                .andExpect(jsonPath("$[1].customerId").value("6"));

    }

    private List<Customer> createListOfCustomers() {

        Tracking tracking1 =new Tracking(1L,"delivered",3L);
        Tracking tracking2 =new Tracking(2L,"returned",3L);
        Tracking tracking3 =new Tracking(3L,"initiated",4L);
        Tracking tracking4 =new Tracking(4L,"cancelled",4L);
        Tracking tracking5 =new Tracking(5L,"returned",5L);


       List<Tracking> statuses1 =Arrays.asList(tracking1,tracking2);
       List<Tracking> statuses2=Arrays.asList(tracking3,tracking4);
       List<Tracking> statuses3=Arrays.asList(tracking5);



        Shipment shipment1 = new Shipment(
                3L,
                "Bosch",
                7L,
                statuses1);
        Shipment shipment2 = new Shipment(
                2L,
                "TP-Link",
                6L,
                statuses2
        );
        Shipment shipment3=new Shipment(
                4L,
                "Notebook",
                6L,
                statuses3
        );
        Customer customer1 = new Customer(
                7L,
                "Ivan",
                Arrays.asList(shipment1));
        Customer customer2=new Customer(6L,
                "Petr",
                Arrays.asList(shipment2,shipment3));


        return Arrays.asList(customer1, customer2);
    }



    @Test
    public void testAddShipment() throws Exception {
        Shipment shipmentEntity = new Shipment(
                null,
                "Sony TV",
                7L,
                null);
        Shipment savedShipmentEntity = new Shipment(
                5L,
                "Sony TV",
                7L,
                null);
        when(service.addShipment(shipmentEntity)).thenReturn(savedShipmentEntity);

        mvc.perform(post("/api/customers/7/shipments")
                .content("{\"description\": \"Sony TV\",\"customerId\":\"7\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.shipmentId").value("5"))
                .andExpect(jsonPath("$.description").value("Sony TV"))
                .andExpect(jsonPath("$.customerId").value("7"));

        verify(service, times(1)).addShipment(shipmentEntity);
    }

}
