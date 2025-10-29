package org.myfss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La raison sociale est obligatoire.")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]+$", message = "La raison sociale contient des caractères invalides.")
    @Column(name = "social_reason")
    private String socialReason;

    @NotBlank(message = "L'adresse est obligatoire.")
    @Pattern(
            regexp = "^[0-9A-Za-zÀ-ÖØ-öø-ÿ'\\-., ]+$",
            message = "L'adresse contient des caractères invalides (lettres, chiffres, espaces, tirets, points et virgules autorisés)."
    )
    @Column(name = "address")
    private String address;

    @Size(max = 2000, message = "Les commentaires ne doivent pas dépasser 2000 caractères.")
    @Column(name = "access_information", columnDefinition = "TEXT")
    private String accessInformation;
}