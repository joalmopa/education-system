package co.com.education.database.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "STUDENTS")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
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

    @Column(name = "GENDER")
    private String gender;
}
