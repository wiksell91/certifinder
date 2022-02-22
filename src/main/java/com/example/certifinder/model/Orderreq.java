package com.example.certifinder.model;




import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table
public class Orderreq {

    @Id
    @SequenceGenerator(
            name = "orderreq_sequence",
            sequenceName = "orderreq_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orderreq_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "certuser_id")
    public Certuser certuser;

    @ManyToOne
    @JoinColumn(name = "company_id")
    public Company company;
    private Orderstatus orderstatus;
    private String ordertype;
    private String comment;
    private LocalDate orderdate;



    public Orderreq() {
    }

    public Orderreq(Certuser certuser, Company company, String ordertype,Orderstatus orderstatus, String comment, LocalDate orderdate) {
        this.id = id;
        this.certuser = certuser;
        this.company = company;
        this.ordertype = ordertype;
        this.comment = comment;
        this.orderdate = orderdate;
        this.orderstatus = orderstatus;
    }


    public Orderstatus getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Orderstatus orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Certuser getCertuser() {
        return certuser;
    }

    public void setCertuser(Certuser certuser) {
        this.certuser = certuser;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(LocalDate orderdate) {
        this.orderdate = orderdate;
    }
}
