package com.example.certifinder.controller;

import com.example.certifinder.model.Certificate;
import com.example.certifinder.model.Certificatestatus;
import com.example.certifinder.model.Certuser;
import com.example.certifinder.service.CertificateService;
import com.example.certifinder.service.CertificatestatusService;
import com.example.certifinder.service.CertuserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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


//    @GetMapping("/{certType}")
//    public Optional<Certificatestatus> getCert(@PathVariable("certType") String certType){
//        return certificatestatusService.getCert(certType);
//    }

    @PostMapping("/addstatus/user/{certuserId}/cert/{certificateId}")
    public void addCertstatus(@RequestBody Certificatestatus certificatestatus, @PathVariable("certuserId") Long certuserId, @PathVariable("certificateId") Long certificateId){
        certificatestatusService.addCertstatus(certificatestatus, certuserId, certificateId);
    }

/*   @PutMapping("/{certicicatestatusId}/certusers/{certuserId}")
    Certificatestatus addCertuserToStatus(@PathVariable("certicicatestatusId") Long certicicatestatusId,
                                         @PathVariable("certuserId") Long certuserId){
        return addCertuserToStatus(certicicatestatusId, certuserId);

    }
    @PutMapping("/{certicicatestatusId}/certificates/{certificateId}")
    Certificatestatus addCertToStatus(@PathVariable("certicicatestatusId") Long certicicatestatusId,
                                          @PathVariable("certificateId") Long certificateId){
        return addCertToStatus(certicicatestatusId, certificateId);

    }*/

}
