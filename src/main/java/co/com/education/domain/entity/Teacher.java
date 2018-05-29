package co.com.education.domain.entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Teacher {

    private Integer id;
    private String documentType;
    private String documentNumber;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private String gender;
}
