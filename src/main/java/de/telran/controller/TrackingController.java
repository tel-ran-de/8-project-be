package de.telran.controller;


import de.telran.dto.TrackingDTO;
import de.telran.entity.Tracking;
import de.telran.service.TrackingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class TrackingController {

    private TrackingService service;

    @Autowired
    public TrackingController(TrackingService service, ModelMapper modelMapper) {
        this.service = service;
    }

    @GetMapping("/api/tracking")
    public List<Tracking> getAllTrackingWithShipmentsAndTrackings() {
        return service.getAllTracking();
    }

    @PostMapping("/api/tracking")
    public Tracking addTracking(@RequestBody TrackingDTO tracking) {

        Date date = new Date();
        Tracking dbTracking = new Tracking(null, tracking.getStatus(), tracking.getShipmentId(), date);

        return service.addTracking(dbTracking);
    }

}
