package co.com.education.domain.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Course {

    private Integer id;
    private String description;
}
