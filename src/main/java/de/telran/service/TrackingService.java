package de.telran.service;

import de.telran.entity.Customer;
import de.telran.entity.Shipment;
import de.telran.entity.Tracking;
import de.telran.repositiory.CustomerRepository;
import de.telran.repositiory.ShipmentRepository;
import de.telran.repositiory.TrackingRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Shipment addShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public Tracking addTracking(Tracking tracking) {
        return trackingRepository.save(tracking);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public List<Shipment> getAllShipments() {
        return null;

    }

    public Tracking getTrackingByShipmentId(Long shipmentId) {

        return trackingRepository.getOne(shipmentId);
    }
}
