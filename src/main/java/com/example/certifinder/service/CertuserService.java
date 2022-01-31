package com.example.certifinder.service;

import com.example.certifinder.exception.BadRequestException;
import com.example.certifinder.model.Certuser;
import com.example.certifinder.repository.CertuserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertuserService {

    private final CertuserRepository certuserRepository;

    @Autowired
    public CertuserService(CertuserRepository certuserRepository) {
        this.certuserRepository = certuserRepository;
    }

    public List<Certuser> getCertuser(){
        return certuserRepository.findAll();
    }
    public void addCertuser(Certuser certuser) {
        Boolean existsEmail = certuserRepository
                .selectExistsEmail(certuser.getEmail());

        if (existsEmail){
            throw new BadRequestException(
                    "Email " + certuser.getEmail() + " är upptaget");
        }

        certuserRepository.save(certuser);
    }
}
