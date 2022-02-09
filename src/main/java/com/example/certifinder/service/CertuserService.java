package com.example.certifinder.service;

import com.example.certifinder.exception.BadRequestException;
import com.example.certifinder.model.Certificatestatus;
import com.example.certifinder.model.Certuser;
import com.example.certifinder.repository.CertificateRepository;
import com.example.certifinder.repository.CertificatestatusRepository;
import com.example.certifinder.repository.CertuserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertuserService {

    private final CertuserRepository certuserRepository;
    private final CertificatestatusRepository certificatestatusRepository;


    @Autowired
    public CertuserService(CertuserRepository certuserRepository, CertificatestatusRepository certificatestatusRepository) {
        this.certuserRepository = certuserRepository;
        this.certificatestatusRepository = certificatestatusRepository;
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

       List<Certificatestatus> certificatestatuses =
               certificatestatusRepository.findCertificatestatusByCertuser(certuser.get());
       certificatestatusRepository.deleteAll(certificatestatuses);
       certuserRepository.deleteById(certuserId);

    }
}
