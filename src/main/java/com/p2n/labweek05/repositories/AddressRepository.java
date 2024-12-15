package com.p2n.labweek05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p2n.labweek05.entities.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
//    List<Address> findByCity(String city);
//    List<Address> findByState(String state);
}
