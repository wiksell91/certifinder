package com.example.certifinder.repository;

import com.example.certifinder.model.Certificate;
import com.example.certifinder.model.Certuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertuserRepository extends JpaRepository<Certuser, Long> {


    @Query("" +
            "SELECT CASE WHEN COUNT(c) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Certuser c " +
            "WHERE c.email = ?1"
    )
    Boolean selectExistsEmail(String email);

    Optional<Certuser> findUserByUsername(String username);
}
