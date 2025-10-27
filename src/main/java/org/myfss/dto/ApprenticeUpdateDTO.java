package org.myfss.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.myfss.model.enums.Major;

@Builder
@Data

public class ApprenticeUpdateDTO {

    @NotBlank(message = "Le programme est obligatoire.")
    @Size(max = 100, message = "Le nom du programme ne doit pas dépasser 100 caractères.")
    private String program;

    @NotBlank(message = "L'année académique est obligatoire.")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}$", message = "L'année académique doit être au format YYYY-YYYY")
    private String academicYear;

    @NotNull(message = "La spécialité est obligatoire.")
    private Major major;

    @NotBlank(message = "Le nom est obligatoire.")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]+$", message = "Le nom contient des caractères invalides.")
    @Size(max = 20)
    private String lastName;

    @NotBlank(message = "Le prénom est obligatoire.")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]+$", message = "Le prénom contient des caractères invalides.")
    @Size(max = 20)
    private String firstName;

    @NotBlank(message = "L'adresse e-mail est obligatoire.")
    @Email(message = "L'adresse e-mail doit être valide et contenir un domaine (ex: exemple@gmail.com).")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "L'adresse e-mail doit être valide et contenir un domaine (ex: exemple@gmail.com)."
    )
    @Size(max = 50)
    private String email;

    @NotBlank(message = "Le numéro de téléphone est obligatoire.")
    @Pattern(regexp = "^\\d{10}$", message = "Le numéro de téléphone doit contenir exactement 10 chiffres.")
    private String phoneNumber;

    private Long companyId;
    private Long masterId;

    @Size(max = 2000)
    private String comments;

    @Size(max = 2000)
    private String tutorFeedback;
}
