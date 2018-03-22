package co.com.education.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "TEACHERS")
public class GroupEntity {

    @Id
    private Integer id;

    @Column(name="OPEN_YEAR")
    private String openYear;

}
