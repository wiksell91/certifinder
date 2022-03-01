package com.example.certifinder.repository;


import com.example.certifinder.model.Certificate;
import com.example.certifinder.model.Certstatus;
import com.example.certifinder.model.Certuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertstatusRepository extends JpaRepository<Certstatus, Long> {

    List<Certstatus> findCertificatestatusByCertificate(Certificate certificate);

    List<Certstatus> findCertificatestatusByCertuser(Certuser certuser);



}
