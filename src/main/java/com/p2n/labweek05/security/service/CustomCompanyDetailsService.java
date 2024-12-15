package com.p2n.labweek05.security.service;

import com.p2n.labweek05.entities.Company;
import com.p2n.labweek05.repositories.CompanyRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomCompanyDetailsService implements UserDetailsService {
    
    private final CompanyRepository companyRepository;
    
    public CustomCompanyDetailsService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String companyName) throws UsernameNotFoundException {
        Company company = companyRepository.findByCompanyName(companyName)
            .orElseThrow(() -> new UsernameNotFoundException("Company not found"));
            
        return org.springframework.security.core.userdetails.User
            .withUsername(company.getCompanyName())
            .password(company.getPassword())
            .authorities(company.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .toList())
            .build();
    }
}