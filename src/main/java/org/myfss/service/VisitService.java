package org.myfss.service;

import org.myfss.model.Visit;

public interface VisitService {

    Visit getVisitById(Long id);

    Visit updateVisit(Long id, Visit updatedVisit);
}