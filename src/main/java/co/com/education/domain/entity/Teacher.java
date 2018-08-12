package co.com.education.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
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
