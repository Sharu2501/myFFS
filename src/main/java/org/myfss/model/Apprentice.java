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
    @Column(name = "program", nullable = false)
    private String program;

    @NotBlank(message = "L'année académique est obligatoire.")
    @Pattern(
            regexp = "^[0-9]{4}-[0-9]{4}$",
            message = "L'année académique doit être au format YYYY-YYYY (ex: 2024-2025)."
    )
    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @NotNull(message = "La spécialité est obligatoire.")
    @Enumerated(EnumType.STRING)
    @Column(name = "major", nullable = false)
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
    @Size(max = 50, message = "L'adresse e-mail ne doit pas dépasser 50 caractères.")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Le numéro de téléphone est obligatoire.")
    @Pattern(
            regexp = "^(\\+\\d{1,3}[- ]?)?\\d{9,12}$",
            message = "Le numéro de téléphone doit être valide (ex: +33 612345678)."
    )
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne
    @JoinColumn(name = "masters")
    private Master master;

    @OneToOne(cascade = CascadeType.ALL)
    private Mission mission;

    @OneToOne(cascade = CascadeType.ALL)
    private Visit visit;

    @OneToOne(cascade = CascadeType.ALL)
    private Evaluation evaluation;

    @Size(max = 2000, message = "Les commentaires ne doivent pas dépasser 2000 caractères.")
    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Size(max = 2000, message = "Le retour du tuteur ne doit pas dépasser 2000 caractères.")
    @Column(name = "tutor_feedback", columnDefinition = "TEXT")
    private String tutorFeedback;
}
