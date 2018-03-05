package co.com.education.database.jpa.entity;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "COURSES")
public class CourseEntity  implements Serializable {

    @Id
    private Integer id;

    @Column(name="DESCRIPTION")
    private String description;
}
