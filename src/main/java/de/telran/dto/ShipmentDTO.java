package de.telran.dto;

import de.telran.entity.Tracking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDTO {
    @Nullable
    Long shipmentId;
    String description;
    Long customerId;
    List<TrackingDTO> statusesDTO;
}
