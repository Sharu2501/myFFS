package org.myfss.service;

import lombok.RequiredArgsConstructor;
import org.myfss.model.Visit;
import org.myfss.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }
}
