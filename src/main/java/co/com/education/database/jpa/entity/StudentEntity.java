package co.com.education.database.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "STUDENTS")
public class StudentEntity {

    @Id
    private Integer id;

    @Column(name="DOC_TYPE")
    private String documentType;

    @Column(name="DOC_NUMBER")
    private String documentNumber;

    @Column(name="NAME")
    private String name;

    @Column(name="PHONE")
    private String phone;

    @Column(name="EMAIL")
    private String email;

    @Column(name="BIRTH_DATE")
    private LocalDate birthDate;
}
