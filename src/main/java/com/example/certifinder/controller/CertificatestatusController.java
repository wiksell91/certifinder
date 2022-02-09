package com.example.certifinder.controller;

import com.example.certifinder.model.Certificatestatus;
import com.example.certifinder.service.CertificatestatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/certstatus")
public class CertificatestatusController {


    private final CertificatestatusService certificatestatusService;

    @Autowired
    public CertificatestatusController(CertificatestatusService certificatestatusService) {
        this.certificatestatusService = certificatestatusService;
    }

    @GetMapping
    public List<Certificatestatus> getAllCertstatus(){
        return certificatestatusService.getAllCertstatus();
    }


    @GetMapping("/cert/{certType}")
    public List<Certificatestatus> getCert(@PathVariable("certType") String certType){
        return certificatestatusService.getCert(certType);
    }

    @GetMapping("/user/{username}")
    public List<Certificatestatus> getUsersCert(@PathVariable("username") String username){
        return certificatestatusService.getUsersCert(username);
    }

    @PostMapping("/addstatus/user/{certuserId}/cert/{certificateId}")
    public void addCertstatus(@RequestBody Certificatestatus certificatestatus, @PathVariable("certuserId") Long certuserId, @PathVariable("certificateId") Long certificateId){
        certificatestatusService.addCertstatus(certificatestatus, certuserId, certificateId);
    }



}
