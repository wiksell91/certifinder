package com.example.certifinder.service;

import com.example.certifinder.exception.BadRequestException;
import com.example.certifinder.model.Certstatus;
import com.example.certifinder.model.Certuser;

import com.example.certifinder.model.Role;
import com.example.certifinder.repository.CertstatusRepository;
import com.example.certifinder.repository.CertuserRepository;
import com.example.certifinder.repository.RoleRepo;
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
public class CertuserService implements UserDetailsService {


    private final CertuserRepository certuserRepository;
    private final CertstatusRepository certstatusRepository;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Certuser certuser = certuserRepository.findCertuserByEmail(username);
        if(certuser == null){
            throw new UsernameNotFoundException("Användaren finns inte");
        }else {
            log.info("Användaren hittades");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        certuser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(certuser.getEmail(), certuser.getPassword(), authorities);
    }

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

    public Role saveRole(Role role){
        return roleRepo.save(role);
    }

    public void addRoleToUser(String email, String roleName){
        Certuser certuser = certuserRepository.findCertuserByEmail(email);
        Role role = roleRepo.findByName(roleName);
        certuser.getRoles().add(role);

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
