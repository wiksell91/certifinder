
package com.example.certifinder.controller;


import com.example.certifinder.model.Certuser;
import com.example.certifinder.service.CertuserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/certuser")
public class CertuserController {

    private final CertuserService certuserService;

    @Autowired
    public CertuserController(CertuserService certuserService) {
        this.certuserService = certuserService;
    }

    @GetMapping
    public List<Certuser> getCertuser(){
        return certuserService.getCertuser();
    }


    @PostMapping
    public void addCertuser(@RequestBody Certuser certuser){
        certuserService.addCertuser(certuser);
    }

    @DeleteMapping(path = "/{certuserId}")
    public void deleteCertuser(@PathVariable("certuserId") Long certuserId){
        certuserService.deleteCertuser(certuserId);
    }
}
