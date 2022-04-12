package com.example.certifinder.service;

import com.example.certifinder.model.Certuser;
import com.example.certifinder.model.Company;
import com.example.certifinder.repository.CertuserRepository;
import com.example.certifinder.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final CertuserRepository certuserRepository;
    private final CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Certuser certuser = certuserRepository.findCertuserByEmail(username);
        Company company = companyRepository.findCompanyByEmail(username);

        if(certuser.getEmail().equals(username)){
            return certuser;
        }
        if(company.getEmail().equals(username)){
            return company;
        }
        else {
            throw new UsernameNotFoundException("Användaren hittades inte");
        }



    }
}
