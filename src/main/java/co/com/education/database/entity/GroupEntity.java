package co.com.education.database.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "groups")
public class GroupEntity {

    @Id
    private Integer id;

    @Column(name="OPEN_YEAR")
    private String openYear;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_COURSE")
    private CourseEntity course;
}
