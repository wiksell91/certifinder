package com.example.certifinder.repository;

import com.example.certifinder.model.Certificate;
import com.example.certifinder.model.Certificatestatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificatestatusRepository extends JpaRepository<Certificatestatus, Long> {

    // Optional<Certificatestatus> findCertList(Long certificate_id);
}
