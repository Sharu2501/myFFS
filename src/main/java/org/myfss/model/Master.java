package org.myfss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "masters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire.")
    @Size(max = 20, message = "Le nom ne doit pas dépasser 20 caractères.")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]+$", message = "Le nom contient des caractères invalides.")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Le prénom est obligatoire.")
    @Size(max = 20, message = "Le prénom ne doit pas dépasser 20 caractères.")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]+$", message = "Le prénom contient des caractères invalides.")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "position")
    private String position;

    @NotBlank(message = "L'adresse e-mail est obligatoire.")
    @Email(message = "L'adresse e-mail doit être valide.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "L'adresse e-mail doit être valide et contenir un domaine (ex: exemple@gmail.com)."
    )
    @Size(max = 50, message = "L'adresse e-mail ne doit pas dépasser 50 caractères.")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Le numéro de téléphone est obligatoire.")
    @Pattern(
            regexp = "^0\\d{9}$",
            message = "Le numéro de téléphone doit contenir exactement 10 chiffres (ex: 0612345678)."
    )
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "comments")
    private String comments;
}
