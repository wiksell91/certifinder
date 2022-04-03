package com.example.certifinder.repository;

import com.example.certifinder.model.Certificate;
import com.example.certifinder.model.Certuser;
import com.example.certifinder.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CertuserRepository extends JpaRepository<Certuser, Long> {


    @Query("" +
            "SELECT CASE WHEN COUNT(c) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Certuser c " +
            "WHERE c.email = ?1"
    )
    Boolean selectExistsEmail(String email);



    Optional<Certuser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Certuser c " +
            "SET c.enabled = TRUE WHERE c.email = ?1")
    int enableCertuser(String email);
}



