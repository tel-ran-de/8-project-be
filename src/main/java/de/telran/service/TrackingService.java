package de.telran.service;


import de.telran.entity.Customer;
import de.telran.entity.Shipment;
import de.telran.entity.Tracking;
import de.telran.exception.CustomerNotFoundException;
import de.telran.repositiory.CustomerRepository;
import de.telran.repositiory.ShipmentRepository;
import de.telran.repositiory.TrackingRepositiory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    public Shipment addShipment(Shipment shipment)throws CustomerNotFoundException {
        Shipment savedShipment;
        try {savedShipment = shipmentRepository.save(shipment);}
        catch (Exception  ex ){throw new CustomerNotFoundException();}
        return savedShipment;
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
