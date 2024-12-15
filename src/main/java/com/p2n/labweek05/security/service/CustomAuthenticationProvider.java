package com.p2n.labweek05.security.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    private final CustomUserDetailsService userDetailsService;
    private final CustomCompanyDetailsService companyDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(
            CustomUserDetailsService userDetailsService,
            CustomCompanyDetailsService companyDetailsService,
            PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.companyDetailsService = companyDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        logger.info("Attempting authentication for username: {}", username);
        logger.debug("Raw password length: {}", password.length());

        // Try user authentication first
        try {
            logger.debug("Trying user authentication...");
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            logger.debug("User found in database: {}", userDetails.getUsername());
            logger.debug("Stored password hash: {}", userDetails.getPassword());
            logger.debug("User authorities: {}", userDetails.getAuthorities());
            
            boolean matches = passwordEncoder.matches(password, userDetails.getPassword());
            logger.debug("Password match result: {}", matches);
            
            if (matches) {
                logger.info("User authentication successful");
                return new UsernamePasswordAuthenticationToken(
                    userDetails, password, userDetails.getAuthorities());
            } else {
                logger.debug("Password doesn't match for user");
                logger.debug("Input password: {}", password);
            }
        } catch (Exception e) {
            logger.debug("User authentication failed: {}", e.getMessage());
            logger.debug("Exception type: {}", e.getClass().getName());
            
            // If user not found, try company authentication
            try {
                logger.debug("Trying company authentication...");
                UserDetails companyDetails = companyDetailsService.loadUserByUsername(username);
                logger.debug("Company found in database: {}", companyDetails.getUsername());
                logger.debug("Stored password hash: {}", companyDetails.getPassword());
                logger.debug("Company authorities: {}", companyDetails.getAuthorities());
                
                boolean matches = passwordEncoder.matches(password, companyDetails.getPassword());
                logger.debug("Password match result: {}", matches);
                
                if (matches) {
                    logger.info("Company authentication successful");
                    return new UsernamePasswordAuthenticationToken(
                        companyDetails, password, companyDetails.getAuthorities());
                } else {
                    logger.debug("Password doesn't match for company");
                    logger.debug("Input password: {}", password);
                }
            } catch (Exception ex) {
                logger.debug("Company authentication failed: {}", ex.getMessage());
                logger.debug("Exception type: {}", ex.getClass().getName());
            }
        }
        
        logger.error("Authentication failed for username: {}", username);
        throw new BadCredentialsException("Invalid username or password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}