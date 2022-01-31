package com.example.certifinder.service;


import com.example.certifinder.exception.BadRequestException;
import com.example.certifinder.model.Certificate;
import com.example.certifinder.repository.CertificateRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    @Autowired
    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public List<Certificate> getAllCertificates(){
        return certificateRepository.findAll();
    }

    public void addCertificate(Certificate certificate){
        Boolean existsCertType = certificateRepository
                .selectExistsCertType(certificate.getCertType());
        if(existsCertType){
            throw new BadRequestException(
                    "Certifikatet " + certificate.getCertType() + " finns redan i systemet"
            );
        }
        certificateRepository.save(certificate);
    }
}
