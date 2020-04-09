package de.telran.service;

import de.telran.dto.CustomerDto;
import de.telran.entity.Customer;
import de.telran.entity.Shipment;
import de.telran.entity.Tracking;
import de.telran.exception.CustomerNotFoundException;
import de.telran.repositiory.CustomerRepository;
import de.telran.repositiory.ShipmentRepository;
import de.telran.repositiory.TrackingRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackingService {


    private CustomerRepository customerRepository;
    private ShipmentRepository shipmentRepository;
    private TrackingRepositiory trackingRepository;

    @Autowired
    public TrackingService(CustomerRepository customerRepository,
                           ShipmentRepository shipmentRepository,
                           TrackingRepositiory trackingRepository) {
        this.customerRepository = customerRepository;
        this.shipmentRepository = shipmentRepository;
        this.trackingRepository = trackingRepository;
    }

    public Customer addCustomer(CustomerDto customerChangeRequestDto) {
        Customer customer = new Customer();
        customer.setName(customerChangeRequestDto.getName());
        return customerRepository.save(customer);
    }


    public Shipment addShipment(Long customerId, Shipment shipment) {
        Customer customer = customerRepository.getById(customerId).orElseThrow(CustomerNotFoundException::new);
        shipment.setCustomer(customer);
        return shipmentRepository.save(shipment);
    }

    public List<Shipment> getShipmentsByCustomerId(Long customerId) {
        Customer customer = customerRepository.getById(customerId).orElseThrow(CustomerNotFoundException::new);
        return shipmentRepository.findAllShipmentsByCustomerId(customerId);
    }


    public Tracking addTracking(Tracking tracking) {
        return trackingRepository.save(tracking);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }




    public Tracking getCustomerByCustomerId(Long customerId) {
        return trackingRepository.getOne(customerId);
    }
}
