package com.example.certifinder.service;


import com.example.certifinder.model.Certuser;
import com.example.certifinder.model.Company;
import com.example.certifinder.model.Orderreq;
import com.example.certifinder.repository.CertuserRepository;
import com.example.certifinder.repository.CompanyRepository;
import com.example.certifinder.repository.OrderreqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.desktop.OpenFilesEvent;
import java.util.List;
import java.util.Optional;

@Service
public class OrderreqService {

    private final CertuserRepository certuserRepository;
    private final OrderreqRepository orderreqRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public OrderreqService(CertuserRepository certuserRepository, OrderreqRepository orderreqRepository, CompanyRepository companyRepository) {
        this.certuserRepository = certuserRepository;
        this.orderreqRepository = orderreqRepository;
        this.companyRepository = companyRepository;
    }

    public List<Orderreq> getAllOrderReqs(){
        return orderreqRepository.findAll();
    }


    public void addOrderReqs(Orderreq orderreq, Long certuserId, Long companyId){
        Certuser certuser = certuserRepository.findById(certuserId).get();
        orderreq.setCertuser(certuser);
        Company company = companyRepository.findById(companyId).get();
        orderreq.setCompany(company);

        orderreqRepository.save(orderreq);

    }

    public List<Orderreq> getCompanyOrders(String email){
        Optional<Company> comp = companyRepository.findByEmail(email);

        return orderreqRepository.findOrderreqByCompany(comp.get());
    }

    public List<Orderreq> getUsersOrders(String email){
        Optional<Certuser> certuser = certuserRepository.findByEmail(email);
        return orderreqRepository.findOrderreqByCertuser(certuser.get());
    }

}
