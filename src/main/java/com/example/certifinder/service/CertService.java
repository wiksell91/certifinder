package com.example.certifinder.service;

import com.example.certifinder.model.Certificate;
import com.example.certifinder.model.Certstatus;
import com.example.certifinder.model.Certuser;
import com.example.certifinder.repository.CertificateRepository;
import com.example.certifinder.repository.CertstatusRepository;
import com.example.certifinder.repository.CertuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CertService {

     private  final CertstatusRepository certstatusRepository;
     private final CertificateRepository certificateRepository;
     private final CertuserRepository certuserRepository;

     @Autowired
     public CertService(CertstatusRepository certstatusRepository, CertificateRepository certificateRepository, CertuserRepository certuserRepository) {
          this.certstatusRepository = certstatusRepository;
          this.certificateRepository = certificateRepository;
          this.certuserRepository = certuserRepository;
     }

     public List<Certstatus> getAllCertstatus(){
          return certstatusRepository.findAll();
     }

     public void addCertstatus(Certstatus certstatus, Long certuserId, Long certificateId){
          Certuser certuser = certuserRepository.findById(certuserId).get();
          certstatus.setCertuser(certuser);
          Certificate certificate = certificateRepository.findById(certificateId).get();
          certstatus.setCertificate(certificate);


          certstatusRepository.save(certstatus);
     }

     public List<Certstatus> getCert(String certType){
          Optional<Certificate> cert = certificateRepository.findCertByType(certType);

          return certstatusRepository.findCertificatestatusByCertificate(cert.get());

     }


     public List<Certstatus> getUsersCert(String username){
          Optional<Certuser> certuser = certuserRepository.findUserByUsername(username);

          return certstatusRepository.findCertificatestatusByCertuser(certuser.get());

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