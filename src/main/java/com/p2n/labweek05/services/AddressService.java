package com.p2n.labweek05.services;

import com.p2n.labweek05.entities.Address;
import com.p2n.labweek05.dtos.AddressDTO;

import java.util.List;

public interface AddressService {
    Address createAddress(AddressDTO addressDTO);
    Address getAddressById(Long id);
    Address updateAddress(Address address);
    void deleteAddress(Long id);
    List<Address> getAllAddresses();
    AddressDTO convertToDTO(Address address);
}
