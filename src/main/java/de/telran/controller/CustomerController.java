package de.telran.controller;


import de.telran.dto.CustomerDto;
import de.telran.dto.ShipmentDTO;
import de.telran.entity.Customer;
import de.telran.entity.Shipment;
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

    @GetMapping("/api/customers")
    public List<Customer> getAllCustomersWithShipmentsAndTrackings() {
        return service.getAllCustomers();
    }

    @GetMapping("/api/customers/{id}")
    public CustomerDto getCustomerById(@PathVariable long id){
       return modelMapper.map(service.getCustomerByCustomerId(id), CustomerDto.class);
    }

    @PostMapping("/api/customers")
    public Customer addCustomer(@RequestBody CustomerDto customer) {
        return service.addCustomer(customer);
    }

    @PostMapping("/api/customers/{customerId}/shipments")
    public Shipment addShipment(@RequestBody ShipmentDTO shipmentDto, @PathVariable Long customerId) {
        return service.addShipment(customerId, convertShipmentDtoToShipment(shipmentDto));
    }

    private Shipment convertShipmentDtoToShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = new Shipment();
        shipment.setDescription(shipmentDTO.getDescription());
        shipment.setId(shipmentDTO.getId());
        return  shipment;
    }

}
