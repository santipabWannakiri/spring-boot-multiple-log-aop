package com.multiple.log.aop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @NotBlank(message = "is mandatory")
    @Size(min = 13, max = 13, message = "IdentificationNumber must be 13 digit")
    private String identificationNumber;

    @NotBlank(message = "is mandatory")
    @Size(min = 2, max = 20, message = "FirstName must not over 20 characters")
    private String firstName;

    @NotBlank(message = "is mandatory")
    @Size(min = 2, max = 20, message = "LastName must not over 20 characters")
    private String lastName;

    @Email
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;


    public Instructor() {
    }

    public Instructor(String identificationNumber, String firstName, String lastName, String email, InstructorDetail instructorDetail) {
        this.identificationNumber = identificationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.instructorDetail = instructorDetail;
    }
}