package com.example.certifinder.controller;

import com.example.certifinder.model.Company;
import com.example.certifinder.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/v1/company")
@RestController
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompany(){
        return companyService.getAllCompany();
    }

    @PostMapping
    public void addCompany(@RequestBody Company company){
        companyService.addCompany(company);
    }
}
