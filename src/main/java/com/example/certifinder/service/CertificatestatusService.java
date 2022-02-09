package com.example.certifinder.service;

import com.example.certifinder.model.Certificate;
import com.example.certifinder.model.Certificatestatus;
import com.example.certifinder.model.Certuser;
import com.example.certifinder.repository.CertificateRepository;
import com.example.certifinder.repository.CertificatestatusRepository;
import com.example.certifinder.repository.CertuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CertificatestatusService {

     private  final CertificatestatusRepository certificatestatusRepository;
     private final CertificateRepository certificateRepository;
     private final CertuserRepository certuserRepository;

     @Autowired
     public CertificatestatusService(CertificatestatusRepository certificatestatusRepository, CertificateRepository certificateRepository, CertuserRepository certuserRepository) {
          this.certificatestatusRepository = certificatestatusRepository;
          this.certificateRepository = certificateRepository;
          this.certuserRepository = certuserRepository;
     }

     public List<Certificatestatus> getAllCertstatus(){
          return certificatestatusRepository.findAll();
     }

     public void addCertstatus(Certificatestatus certificatestatus, Long certuserId, Long certificateId){
          Certuser certuser = certuserRepository.findById(certuserId).get();
          certificatestatus.setCertuser(certuser);
          Certificate certificate = certificateRepository.findById(certificateId).get();
          certificatestatus.setCertificate(certificate);


          certificatestatusRepository.save(certificatestatus);
     }

     public List<Certificatestatus> getCert(String certType){
          Optional<Certificate> certificate = certificateRepository.findCertByType(certType);

          return certificatestatusRepository.findCertificatestatusByCertificate(certificate.get());

     }

   /*  public void addCertstatus(Certificatestatus certificatestatus){
          certificatestatusRepository.save(certificatestatus);
     }*/

/*     public void addCertuserToStatus(Long certificatestatusId, Long certuserId){
          Certuser certuser = certuserRepository.findById(certuserId).get();
          Certificatestatus certificatestatus = certificatestatusRepository.findById(certificatestatusId).get();
          certificatestatus.setCertuser(certuser);
          certificatestatusRepository.save(certificatestatus);
     }

     public void addCertToStatus(Long certificatestatusId, Long certificateId) {
          Certificate certificate = certificateRepository.findById(certificateId).get();
          Certificatestatus certificatestatus = certificatestatusRepository.findById(certificatestatusId).get();
          certificatestatus.setCertificate(certificate);
          certificatestatusRepository.save(certificatestatus);
     }*/
}
