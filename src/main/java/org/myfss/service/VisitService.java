package org.myfss.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.VisitExceptions.*;
import org.myfss.model.Visit;
import org.myfss.repository.VisitRepository;
import org.myfss.util.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    public Visit getVisitById(Long id) {
        return visitRepository.findById(id)
                .orElseThrow(() -> new VisitNotFoundException(id));
    }

    @Transactional
    public Visit updateVisit(Long id, Visit updatedVisit) {
        Visit existingVisit = getVisitById(id);

        applyChanges(existingVisit, updatedVisit);
        validateVisitRequiredFields(existingVisit);

        return visitRepository.save(existingVisit);
    }

    private void validateVisitRequiredFields(Visit visit) {
        ValidationUtils.validateRequiredFields(
                Map.of(
                        "date", visit.getDate(),
                        "format", visit.getFormat(),
                        "commentaire", visit.getComments()
                ),
                InvalidVisitDataException::new
        );
    }

    private void applyChanges(Visit target, Visit source) {
        if (!source.getDate().equals(target.getDate())) {
            target.setDate(source.getDate());
        }

        if (!source.getFormat().equals(target.getFormat())) {
            target.setFormat(source.getFormat());
        }

        if (!source.getComments().equals(target.getComments())) {
            target.setComments(source.getComments());
        }
    }
}
