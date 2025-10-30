package org.myfss.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.EvaluationExceptions.*;
import org.myfss.model.Oral;
import org.myfss.repository.OralRepository;
import org.myfss.util.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OralServiceImpl implements OralService {

    private final OralRepository oralRepository;

    public Oral getOralById(Long id) {
        return oralRepository.findById(id)
                .orElseThrow(() -> new OralNotFoundException(id));
    }

    @Transactional
    public Oral updateOral(Long id, Oral updatedOral) {
        Oral existingOral = getOralById(id);

        if (existingOral.getComments() != null) {
            validateOralRequiredFields(updatedOral);
        }

        applyChanges(existingOral, updatedOral);

        return oralRepository.save(existingOral);
    }

    private void validateOralRequiredFields(Oral oral) {
        ValidationUtils.validateRequiredFields(
                Map.of(
                        "date", oral.getDate(),
                        "grade", oral.getGrade(),
                        "comments", oral.getComments()
                ),
                InvalidEvaluationDataException::new
        );
    }

    private void applyChanges(Oral target, Oral source) {
        if (source.getDate() != null && !Objects.equals(source.getDate(), target.getDate())) {
            target.setDate(source.getDate());
        }

        if (source.getGrade() != null && !Objects.equals(source.getGrade(), target.getGrade())) {
            target.setGrade(source.getGrade());
        }

        if (source.getComments() != null) {
            target.setComments(source.getComments());
        }
    }
}
