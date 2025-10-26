package org.myffs.model;

import jakarta.persistence.*;
import lombok.*;
import org.myffs.model.enums.Format;

import java.time.LocalDate;

@Entity
@Table(name = "visits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "format")
    private Format format;

    @Column(name = "comments")
    private String comments;
}
