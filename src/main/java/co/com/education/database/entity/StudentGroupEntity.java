package co.com.education.database.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "STUDENTS_X_GROUP")
public class StudentGroupEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Integer id;

    @Column(name = "ID_GROUP")
    private Integer idGroup;

    @Column(name = "ID_STUDENT")
    private Integer idStudent;

    @Column(name = "MARK")
    private double mark;

    @Column(name = "IDPERIODO")
    private Integer idPeriodo;


}
