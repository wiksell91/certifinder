package com.example.certifinder.service;


import com.example.certifinder.exception.BadRequestException;
import com.example.certifinder.model.Company;

import com.example.certifinder.repository.CompanyRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.List;


@AllArgsConstructor
@Service
public class CompanyService  {


    private final CompanyRepository companyRepository;




    public List<Company> getAllCompany(){
        return companyRepository.findAll();
    }

    public void addCompany(Company company){
        Boolean existsCompany = companyRepository
                .selectExistsCompany(company.getFullName());

        if (existsCompany){
            throw new BadRequestException(
                    "Företaget " + company.getFullName() + " finns redan i systemet"
            );
        }

        companyRepository.save(company);
    }

}
