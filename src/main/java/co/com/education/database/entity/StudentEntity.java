package co.com.education.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
