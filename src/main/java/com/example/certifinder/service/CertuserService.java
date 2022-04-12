package com.example.certifinder.service;

import com.example.certifinder.exception.BadRequestException;
import com.example.certifinder.model.Certstatus;
import com.example.certifinder.model.Certuser;

import com.example.certifinder.repository.CertstatusRepository;
import com.example.certifinder.repository.CertuserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@Service
@AllArgsConstructor
public class CertuserService {


    private final CertuserRepository certuserRepository;
    private final CertstatusRepository certstatusRepository;
    private final PasswordEncoder passwordEncoder;



    public List<Certuser> getAllCertuser(){
        return certuserRepository.findAll();
    }


    public Certuser addCertuser(Certuser certuser) {
        Boolean existsEmail = certuserRepository
                .selectExistsEmail(certuser.getEmail());
        if (existsEmail){
            throw new BadRequestException(
                    "Email " + certuser.getEmail() + " är upptaget");
        }
        certuser.setPassword(passwordEncoder.encode(certuser.getPassword()));
        return certuserRepository.save(certuser);
    }
    

    public Certuser getCertuser(String email){
        return certuserRepository.findCertuserByEmail(email);
    }


    public void deleteCertuser(Long certuserId){
       Optional<Certuser> certuser = certuserRepository.findById(certuserId);

       List<Certstatus> certstatuses =
               certstatusRepository.findCertificatestatusByCertuser(certuser.get());
       certstatusRepository.deleteAll(certstatuses);
       certuserRepository.deleteById(certuserId);

    }
}
