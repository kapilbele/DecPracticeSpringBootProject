package com.Preparation.SpringBootProject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "StudentDetails")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_sequence", allocationSize = 1)
    @Column(name = "sid")
    private Long id;
    @NotNull
    @Column(name = "sname")
    private String name;
    @NotNull
    @Column(name = "deptName")
    private String dept;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @NotNull
    @Column(name = "mobileNo")
    @Pattern(regexp = "^(\\+91)?[6-9]\\d{9}$")
    @Length(min = 10,max = 10,message = "mobile number length should be having 10 digit")
    private String mobile;
    @Email
    @NotNull
    private String email;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Address> addressess;

    public Student() {
    }

    public Student(Long id, String name, String dept, LocalDate dob, String mobile, String email, List<Address> addressess) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.dob = dob;
        this.mobile = mobile;
        this.email = email;
        this.addressess = addressess;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Address> getAddressess() {
        return addressess;
    }

    public void setAddressess(List<Address> addressess) {
        this.addressess = addressess;
    }
}