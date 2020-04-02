package de.telran.controller;


import de.telran.dto.CustomerDTO;
import de.telran.dto.ShipmentDTO;
import de.telran.entity.Customer;
import de.telran.entity.Shipment;
import de.telran.exception.CustomerNotFoundException;
import de.telran.service.TrackingService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final TrackingService service;
    private final ModelMapper modelMapper;

    public CustomerController(TrackingService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

   /* @GetMapping("/api/customers")
    List<CustomerDTO> getAllCustomersWithShipmentsAndTrackings() {
        return service.getAllCustomers()
                .stream()
                .map(s -> modelMapper.map(s, CustomerDTO.class))
                .collect(Collectors.toList());
    }*/

    @GetMapping("/api/customers")
    public List<Customer> getAllCustomersWithShipmentsAndTrackings() {
        return service.getAllCustomers();
    }

    @GetMapping("/api/customers/{id}")
    public CustomerDTO getCustomerById(@PathVariable long id){
       return modelMapper.map(service.getCustomerByCustomerId(id), CustomerDTO.class);
    }

    @PostMapping("/api/customers")
    CustomerDTO addCustomer(@RequestBody CustomerDTO customer) {
        Customer customerEntity = modelMapper.map(customer, Customer.class);
        return modelMapper.map(service.addCustomer(customerEntity), CustomerDTO.class);
    }

    @PostMapping("/api/customers/{id}/shipments")
    ShipmentDTO addShipment(@RequestBody ShipmentDTO shipment, @PathVariable long id) throws CustomerNotFoundException {
        Shipment shipmentEntity =
                new Shipment(shipment.getShipmentId(), shipment.getDescription(), id, null);
        return modelMapper.map(service.addShipment(shipmentEntity), ShipmentDTO.class);
    }

}
