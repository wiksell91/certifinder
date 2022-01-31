package com.example.certifinder.service;


import com.example.certifinder.exception.BadRequestException;
import com.example.certifinder.model.Company;
import com.example.certifinder.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompany(){
        return companyRepository.findAll();
    }

    public void addCompany(Company company){
        Boolean existsCompany = companyRepository
                .selectExistsCompany(company.getCompanyname());

        if (existsCompany){
            throw new BadRequestException(
                    "Företaget " + company.getCompanyname() + " finns redan i systemet"
            );
        }
        Boolean existsOrgnumber = companyRepository
                .selectExistsOrgnumber(company.getOrgnumber());

        if (existsOrgnumber){
            throw new BadRequestException(
                    "Org-nummer " + company.getOrgnumber() + " finns redan i systemet"
            );
        }
        companyRepository.save(company);
    }

}
