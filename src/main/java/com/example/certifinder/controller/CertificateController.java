package com.example.certifinder.controller;


import com.example.certifinder.model.Certificate;
import com.example.certifinder.service.CertificateService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RequestMapping(path = "/api/v1/certificate")
@RestController
public class CertificateController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public List<Certificate> getAllCertificates(){
        return certificateService.getAllCertificates();
    }

    @PostMapping
    public void addCertificate(@RequestBody Certificate certificate){
        certificateService.addCertificate(certificate);
    }
}