package org.myfss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.myfss.model.enums.Major;

@Entity
@Table(name = "apprentices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Apprentice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le programme est obligatoire.")
    @Size(max = 100, message = "Le nom du programme ne doit pas dépasser 100 caractères.")
    @Column(name = "program")
    private String program;

    @NotBlank(message = "L'année académique est obligatoire.")
    @Pattern(
            regexp = "^[0-9]{4}-[0-9]{4}$",
            message = "L'année académique doit être au format YYYY-YYYY (ex: 2024-2025)."
    )
    @Column(name = "academic_year")
    private String academicYear;

    @NotNull(message = "La spécialité est obligatoire.")
    @Enumerated(EnumType.STRING)
    @Column(name = "major")
    private Major major;

    @NotBlank(message = "Le nom est obligatoire.")
    @Size(max = 20, message = "Le nom ne doit pas dépasser 20 caractères.")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]+$", message = "Le nom contient des caractères invalides.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Le prénom est obligatoire.")
    @Size(max = 20, message = "Le prénom ne doit pas dépasser 20 caractères.")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]+$", message = "Le prénom contient des caractères invalides.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

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
            regexp = "^\\d{10}$",
            message = "Le numéro de téléphone doit contenir exactement 10 chiffres (ex: 0612345678)."
    )
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "masters")
    private Master master;

    @OneToOne(cascade = CascadeType.ALL)
    private Mission mission;

    @OneToOne(cascade = CascadeType.ALL)
    private Visit visit;

    @OneToOne(cascade = CascadeType.ALL)
    private Evaluation evaluation;

    @Size(max = 2000, message = "Les commentaires ne doivent pas dépasser 2000 caractères.")
    @Column(name = "comments")
    private String comments;

    @Size(max = 2000, message = "Le retour du tuteur ne doit pas dépasser 2000 caractères.")
    @Column(name = "tutor_feedback")
    private String tutorFeedback;
}
