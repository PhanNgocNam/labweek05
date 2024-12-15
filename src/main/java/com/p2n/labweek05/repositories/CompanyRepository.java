package com.p2n.labweek05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p2n.labweek05.entities.Company;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    // List<Company> findByCompanyName(String companyName);
    Optional<Company> findByCompanyName(String companyName);

    Optional<Company> findByContactEmail(String contactEmail);

    Optional<Company> findByContactPhone(String contactPhone);
}
