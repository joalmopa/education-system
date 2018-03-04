package co.com.education.database.jpa.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name ="STUDENTS")
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;


    @Column(name="DOC_TYPE", nullable= false)
    private String docType;

    @Column(name="DOC_NUMBER", nullable= false)
    private String docNumber;

    @Column(name="NAME", nullable= false)
    private String name;

    @Column(name="PHONE", nullable= false)
    private String phone;

    @Column(name="EMAIL", nullable= false)
    private String email;

    @Column(name="BIRTH_DATE", nullable= false)
    private LocalDate birthDate;
}
