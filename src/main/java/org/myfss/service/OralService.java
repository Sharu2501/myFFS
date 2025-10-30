package org.myfss.service;

import org.myfss.model.Oral;

public interface OralService {

    Oral getOralById(Long id);

    Oral updateOral(Long id, Oral updatedOral);
}