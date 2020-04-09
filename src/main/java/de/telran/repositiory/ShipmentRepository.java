package de.telran.repositiory;

import de.telran.entity.Customer;
import de.telran.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment,Long> {

    List<Shipment> findAllShipmentsByCustomerId(Long CustomerId);
}
