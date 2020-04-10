package de.telran.controller;

import de.telran.dto.TrackingDTO;
import de.telran.entity.Shipment;
import de.telran.entity.Tracking;
import de.telran.service.TrackingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShipmentController {

    private TrackingService service;

    private ModelMapper modelMapper;

    @Autowired
    public ShipmentController(TrackingService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/shipment")
    public List<Shipment> getAllTrackingWithShipmentsAndTrackings() {
        return service.getAllShipment();
    }
    @PostMapping("/api/shipments/{id}/trackings")
    TrackingDTO addTracking(@RequestBody TrackingDTO tracking, @PathVariable long id) {
        Tracking trackingEntity = new Tracking(tracking.getTrackingId(), tracking.getStatus(), id);
        return modelMapper.map(service.addTracking(trackingEntity), TrackingDTO.class);
    }

}
