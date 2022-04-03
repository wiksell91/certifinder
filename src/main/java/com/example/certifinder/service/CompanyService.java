package com.example.certifinder.service;


import com.example.certifinder.exception.BadRequestException;
import com.example.certifinder.model.Company;
import com.example.certifinder.registration.token.ConfirmationCompanyToken;
import com.example.certifinder.registration.token.ConfirmationUserToken;
import com.example.certifinder.registration.token.ConfirmationTokenService;
import com.example.certifinder.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CompanyService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return companyRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpCompany(Company company){
        boolean companyExist = companyRepository.findByEmail(company.getEmail())
                .isPresent();
        if(companyExist) {
            throw new IllegalStateException("Email is already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(company.getPassword());

        company.setPassword(encodedPassword);

        companyRepository.save(company);

        String token = UUID.randomUUID().toString();
        ConfirmationCompanyToken confirmationCompanyToken = new ConfirmationCompanyToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                company
        );

        confirmationTokenService.saveConfirmationCompanyToken(
                 confirmationCompanyToken
        );

        return token;
    }

    public int enablecompany(String email) {
        return companyRepository.enableCompany(email);
    }


    public List<Company> getAllCompany(){
        return companyRepository.findAll();
    }

    public void addCompany(Company company){
        Boolean existsCompany = companyRepository
                .selectExistsCompany(company.getFullName());

        if (existsCompany){
            throw new BadRequestException(
                    "Företaget " + company.getFullName() + " finns redan i systemet"
            );
        }

        companyRepository.save(company);
    }

}
