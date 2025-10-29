package org.myfss.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oral_id")
    private Oral oral;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id")
    private Report report;
}
