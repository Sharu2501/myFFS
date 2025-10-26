package org.myfss.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "oral_exams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Oral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "comments")
    private String comments;
}
