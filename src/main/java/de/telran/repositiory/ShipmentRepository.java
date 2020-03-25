package de.telran.repositiory;

import de.telran.entity.Customer;
import de.telran.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
}
