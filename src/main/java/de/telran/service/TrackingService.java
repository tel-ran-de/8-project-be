package de.telran.service;

import de.telran.dto.CustomerDto;
import de.telran.dto.ShipmentNameDTO;
import de.telran.dto.TrackingDTO;
import de.telran.entity.Customer;
import de.telran.entity.Shipment;
import de.telran.entity.Tracking;
import de.telran.exception.CustomerNotFoundException;
import de.telran.repositiory.CustomerRepository;
import de.telran.repositiory.ShipmentRepository;
import de.telran.repositiory.TrackingRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Customer addCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.getById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        customer.setName(customerDto.getName());
        return customerRepository.save(customer);
    }

    public Shipment addShipment(Long customerId, Shipment shipment) {
        Customer customer = customerRepository
                .getById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        shipment.setCustomer(customer);
        return shipmentRepository.save(shipment);
    }

    public List<Tracking> getTrackingsByShipmentId(int shipmentId ) {
        List<Tracking> trackings = trackingRepository.findAll();

        return trackings.stream().filter( tracking -> null != tracking.getShipmentId() && tracking.getShipmentId() == shipmentId ).collect(Collectors.toList());
    }

    public List<Shipment> getShipmentsByCustomerId(Long customerId) {
        return shipmentRepository.findAllShipmentsByCustomerId(customerId);
    }

    public ShipmentNameDTO getCustomerByShipmentId(long shipmentId){
        Shipment shipment = shipmentRepository.getOne(shipmentId);
        long customerId =shipment.getCustomer().getId();
        Optional<Customer> customer = customerRepository.getById(customerId);
        customer.orElseThrow(()->new CustomerNotFoundException(customerId));
       return new ShipmentNameDTO(customer.get().getName(),shipment.getDescription());
    }

    public Tracking addTracking(Tracking tracking) {
        return trackingRepository.save(tracking);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByCustomerId(Long customerId) {
        return customerRepository.getOne(customerId);
    }

    public List<Tracking> getAllTracking() {
        return trackingRepository.findAll();
    }

    public List<Shipment> getAllShipment() {
        return shipmentRepository.findAll();
    }
}
