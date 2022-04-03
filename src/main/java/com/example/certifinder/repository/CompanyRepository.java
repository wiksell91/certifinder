package com.example.certifinder.repository;


import com.example.certifinder.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("" +
            "SELECT CASE WHEN COUNT(c) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Company c " +
            "WHERE c.fullName = ?1"
    )
    Boolean selectExistsCompany(String companyname);



    Optional<Company> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Company c " +
            "SET c.enabled = TRUE WHERE c.email = ?1")
    int enableCompany(String email);
}
