package org.myfss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "La raison sociale est obligatoire.")
    @Size(max = 50, message = "La raison sociale ne doit pas dépasser 50 caractères.")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]+$", message = "La raison sociale contient des caractères invalides.")
    @Column(name = "social_reason")
    private String socialReason;

    @NotBlank(message = "L'adresse est obligatoire.")
    @Size(max = 100, message = "L'adresse ne doit pas dépasser 100 caractères.")
    @Column(name = "address")
    private String address;

    @Column(name = "access_information", columnDefinition = "TEXT")
    private String accessInformation;
}
