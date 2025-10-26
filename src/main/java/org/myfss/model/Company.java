package org.myfss.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "social_reason")
    private String socialReason;

    @Column(name = "address")
    private String address;

    @Column(name = "access_information", columnDefinition = "TEXT")
    private String accessInformation;
}
