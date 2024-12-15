package com.p2n.labweek05.services;

import com.p2n.labweek05.dtos.CompanyDTO;
import com.p2n.labweek05.entities.Company;

import java.util.List;

public interface CompanyService {
    Company createCompany(CompanyDTO company);
    Company getCompanyById(Long id);
    Company updateCompany(Company company);
    void deleteCompany(Long id);
    List<Company> getAllCompanies();
}
