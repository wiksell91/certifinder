package com.example.certifinder.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@ToString
@Entity
@Table
public class Certificatestatus {

    @Id
    @SequenceGenerator(
            name = "certificatestatus_sequence",
            sequenceName = "certificatestatus_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "certificatestatus_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "certuser_id")
    public Certuser certuser;

    @ManyToOne
    @JoinColumn(name = "certificate_id")
    public Certificate certificate;

    private LocalDate validto;
    private String regnumber;
    private String generalinfo;

    public Certificatestatus() {
    }

        public Certificatestatus(LocalDate validto, String regnumber, Certuser certuser, Certificate certificate, String generalinfo) {
        this.validto = validto;
        this.regnumber = regnumber;
        this.certuser = certuser;
        this.certificate = certificate;
        this.generalinfo = generalinfo;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getValidto() {
        return validto;
    }

    public void setValidto(LocalDate validto) {
        this.validto = validto;
    }

    public String getRegnumber() {
        return regnumber;
    }

    public void setRegnumber(String regnumber) {
        this.regnumber = regnumber;
    }

    public String getGeneralinfo() {
        return generalinfo;
    }

    public void setGeneralinfo(String generalinfo) {
        this.generalinfo = generalinfo;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Certuser getCertuser() {
        return certuser;
    }

    public void setCertuser(Certuser certuser) {
        this.certuser = certuser;
    }
}
