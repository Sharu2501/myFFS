package org.myfss.service;

import lombok.RequiredArgsConstructor;
import org.myfss.model.Oral;
import org.myfss.repository.OralRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OralService {

    private final OralRepository oralRepository;

    public List<Oral> getAllOrals() {
        return oralRepository.findAll();
    }
}
