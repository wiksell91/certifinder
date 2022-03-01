package com.example.certifinder.service;

import com.example.certifinder.exception.BadRequestException;
import com.example.certifinder.model.Certstatus;
import com.example.certifinder.model.Certuser;
import com.example.certifinder.repository.CertstatusRepository;
import com.example.certifinder.repository.CertuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertuserService {

    private final CertuserRepository certuserRepository;
    private final CertstatusRepository certstatusRepository;


    @Autowired
    public CertuserService(CertuserRepository certuserRepository, CertstatusRepository certstatusRepository) {
        this.certuserRepository = certuserRepository;
        this.certstatusRepository = certstatusRepository;
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
