package de.telran.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingDTO {
    @Nullable
    Long trackingId;
    String status;
    Long shipmentId;
    Date eventDate;
}
