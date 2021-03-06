package com.example.certifinder.repository;


import com.example.certifinder.model.Certificate;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    @Query("" +
            "SELECT CASE WHEN COUNT(c) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Certificate c " +
            "WHERE c.certType = ?1"
    )
    Boolean selectExistsCertType(String certType);

    //Optional<Certificate> findCertByType(String certType);
}
