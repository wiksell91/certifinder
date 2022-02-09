package com.example.certifinder.repository;


import com.example.certifinder.model.Certuser;
import com.example.certifinder.model.Company;
import com.example.certifinder.model.Orderreq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderreqRepository extends JpaRepository<Orderreq, Long> {

    List<Orderreq> findOrderreqByCompany(Company company);
    List<Orderreq> findOrderreqByCertuser(Certuser certuser);
}
