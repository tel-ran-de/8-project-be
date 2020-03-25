package de.telran.controller;


import de.telran.dto.ShipmentDTO;
import de.telran.dto.TrackingDTO;
import de.telran.entity.Shipment;
import de.telran.entity.Tracking;
import de.telran.service.TrackingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrackingController {

    private TrackingService service;

    private ModelMapper modelMapper;

    @Autowired
    public TrackingController(TrackingService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

}
