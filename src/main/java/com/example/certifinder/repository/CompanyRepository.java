package com.example.certifinder.repository;

import com.example.certifinder.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("" +
            "SELECT CASE WHEN COUNT(c) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Company c " +
            "WHERE c.companyname = ?1"
    )
    Boolean selectExistsCompany(String companyname);

    @Query("" +
            "SELECT CASE WHEN COUNT(o) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Company o " +
            "WHERE o.orgnumber = ?1"
    )
    Boolean selectExistsOrgnumber(String orgnumber);
}
