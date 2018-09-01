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
@Table(name = "PERIOD")
public class PeriodEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPERIODO", updatable = false)
    private Integer id;

    @Column(name = "NUM_PERIODO")
    private Integer numPeriod ;

    @Column(name = "PORCENTAGE")
    private double porcentage;



}


