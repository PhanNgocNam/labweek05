package com.p2n.labweek05.services;

import com.p2n.labweek05.dtos.CompanyDTO;
import com.p2n.labweek05.entities.Address;
import com.p2n.labweek05.entities.Company;
import com.p2n.labweek05.repositories.CompanyRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company createCompany(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setCompanyName(companyDTO.getCompanyName());
        company.setContactPhone(companyDTO.getContactPhone());
        company.setContactEmail(companyDTO.getContactEmail());

        Address address = new Address();
        address.setStreet(companyDTO.getAddressDTO().getStreet());
        address.setCity(companyDTO.getAddressDTO().getCity());
        address.setPostalCode(companyDTO.getAddressDTO().getPostalCode());
        address.setCountryCode(companyDTO.getAddressDTO().getCountryCode());
        company.setCompanyAddress(address);

        return companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
