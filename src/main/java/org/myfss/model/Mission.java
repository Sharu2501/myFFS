package org.myfss.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "missions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "profession")
    private String profession;

    @Column(name = "comments")
    private String comments;
}
