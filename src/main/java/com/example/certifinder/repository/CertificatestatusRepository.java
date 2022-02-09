package com.example.certifinder.repository;


import com.example.certifinder.model.Certificate;
import com.example.certifinder.model.Certificatestatus;
import com.example.certifinder.model.Certuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CertificatestatusRepository extends JpaRepository<Certificatestatus, Long> {

    List<Certificatestatus> findCertificatestatusByCertificate(Certificate certificate);

    List<Certificatestatus> findCertificatestatusByCertuser(Certuser certuser);



}
