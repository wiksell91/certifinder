package com.example.certifinder.service;

import com.example.certifinder.exception.BadRequestException;
import com.example.certifinder.model.Certstatus;
import com.example.certifinder.model.Certuser;
import com.example.certifinder.registration.token.ConfirmationUserToken;
import com.example.certifinder.registration.token.ConfirmationTokenService;
import com.example.certifinder.repository.CertstatusRepository;
import com.example.certifinder.repository.CertuserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CertuserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final CertuserRepository certuserRepository;
    private final CertstatusRepository certstatusRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return certuserRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));

    }

    public String signUpUser(Certuser certuser){
        boolean userExist = certuserRepository.findByEmail(certuser.getEmail())
                .isPresent();
        if(userExist) {
            throw new IllegalStateException("Email is already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(certuser.getPassword());

        certuser.setPassword(encodedPassword);

        certuserRepository.save(certuser);

        String token = UUID.randomUUID().toString();
        ConfirmationUserToken confirmationUserToken = new ConfirmationUserToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                certuser
        );

        confirmationTokenService.saveConfirmationUserToken(
                confirmationUserToken
        );

        return token;
    }

    public int enablecertuser(String email) {
        return certuserRepository.enableCertuser(email);
    }

    public List<Certuser> getCertuser(){
        return certuserRepository.findAll();
    }


    public void addCertuser(Certuser certuser) {
        Boolean existsEmail = certuserRepository
                .selectExistsEmail(certuser.getEmail());

        if (existsEmail){
            throw new BadRequestException(
                    "Email " + certuser.getEmail() + " är upptaget");
        }

        certuserRepository.save(certuser);
    }

    public void deleteCertuser(Long certuserId){
       Optional<Certuser> certuser = certuserRepository.findById(certuserId);

       List<Certstatus> certstatuses =
               certstatusRepository.findCertificatestatusByCertuser(certuser.get());
       certstatusRepository.deleteAll(certstatuses);
       certuserRepository.deleteById(certuserId);

    }
}
