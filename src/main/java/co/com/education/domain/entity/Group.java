package co.com.education.domain.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Group {

    private Integer id;
    private String openYear;
    private Course course;
}
