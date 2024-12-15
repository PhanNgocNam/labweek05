package com.p2n.labweek05.controllers;

import com.p2n.labweek05.dtos.AddressDTO;
import com.p2n.labweek05.entities.Address;
import com.p2n.labweek05.services.AddressService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Endpoint to retrieve all addresses
     *
     * @return A list of all addresses
     */
    @GetMapping("/addresses")
    public List<AddressDTO> getAllAddresses() {
        // Call the method in AddressService to get all addresses
        return addressService.getAllAddresses().stream()
                .map(address -> addressService.convertToDTO(address))
                .toList();
    }
    
    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        Address createdAddress = addressService.createAddress(addressDTO);
        AddressDTO createdAddressDTO = addressService.convertToDTO(createdAddress);
        System.out.println(createdAddressDTO);
        return new ResponseEntity<>(createdAddressDTO, HttpStatus.CREATED);
    }
}
