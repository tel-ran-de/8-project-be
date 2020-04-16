package de.telran.controller;

import de.telran.dto.ShipmentNameDTO;
import de.telran.dto.TrackingDTO;
import de.telran.entity.Shipment;
import de.telran.entity.Tracking;
import de.telran.service.TrackingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/api/shipment/{id}")
    public List<Tracking> getTrackingsById(@PathVariable("id") int shipmentId ) {
        List<Tracking> trackingList = service.getTrackingsByShipmentId(shipmentId);

        return trackingList;
    }
    @GetMapping("/api/shipments/{shipmentId}")
    public ShipmentNameDTO getCustomerNameByShipmentId(@PathVariable long shipmentId){
        return service.getCustomerByShipmentId(shipmentId);
    }

    @PostMapping("/api/shipments/{id}/trackings")
    TrackingDTO addTracking(@RequestBody TrackingDTO tracking, @PathVariable long id) {

        Date date = new Date();
        Tracking trackingEntity = new Tracking(tracking.getTrackingId(),tracking.getStatus(), id, date);

        return modelMapper.map(service.addTracking(trackingEntity), TrackingDTO.class);
    }

}
