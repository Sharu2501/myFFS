package org.myfss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Les mots-clés sont obligatoire.")
    @Size(max = 100, message = "Les mots-clés ne doivent pas dépasser 100 caractères.")
    @Pattern(
            regexp = "^[A-Za-z0-9À-ÖØ-öø-ÿ\\-]+(,\\s*[A-Za-z0-9À-ÖØ-öø-ÿ\\-]+)*$",
            message = "Les mots-clés doivent être séparés par des virgules (ex: 'API, SpringBoot, REST') et ne doivent contenir que des lettres, chiffres ou tirets."
    )
    @Column(name = "keywords")
    private String keywords;

    @NotBlank(message = "La profession est obligatoire.")
    @Size(max = 30, message = "La profession ne doit pas dépasser 30 caractères.")
    @Column(name = "profession")
    private String profession;

    @Size(max = 2000, message = "Les commentaires ne doivent pas dépasser 2000 caractères.")
    @Column(name = "comments")
    private String comments;
}
