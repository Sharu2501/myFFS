package org.myfss.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CompanyValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CompanyValidation {
    String message() default "Les informations de l'entreprise sont incomplètes ou invalides.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}