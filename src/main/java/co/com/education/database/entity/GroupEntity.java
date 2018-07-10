package co.com.education.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
