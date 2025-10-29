package org.myfss.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.myfss.model.Apprentice;
import org.myfss.model.Company;

public class CompanyValidator implements ConstraintValidator<CompanyValidation, Apprentice> {

    @Override
    public boolean isValid(Apprentice apprentice, ConstraintValidatorContext context) {
        Company company = apprentice.getCompany();

        if (company == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("L'entreprise est obligatoire.")
                    .addPropertyNode("company")
                    .addConstraintViolation();
            return false;
        }

        if (company.getId() != null) {
            return true;
        }

        boolean valid = true;

        if (company.getSocialReason() == null || company.getSocialReason().isBlank()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("La raison sociale est obligatoire.")
                    .addPropertyNode("company.socialReason")
                    .addConstraintViolation();
            valid = false;
        } else if (!company.getSocialReason().matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9 '.,\\-]+$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("La raison sociale contient des caractères invalides.")
                    .addPropertyNode("company.socialReason")
                    .addConstraintViolation();
            valid = false;
        }

        if (company.getAddress() == null || company.getAddress().isBlank()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("L'adresse est obligatoire.")
                    .addPropertyNode("company.address")
                    .addConstraintViolation();
            valid = false;
        } else if (!company.getAddress().matches("^[A-Za-z0-9À-ÖØ-öø-ÿ ',.\\-]+$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("L'adresse contient des caractères invalides.")
                    .addPropertyNode("company.address")
                    .addConstraintViolation();
            valid = false;
        }

        if (company.getAccessInformation() != null && company.getAccessInformation().length() > 2000) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Les informations d'accès ne doivent pas dépasser 2000 caractères.")
                    .addPropertyNode("company.accessInformation")
                    .addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}