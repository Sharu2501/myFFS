package org.myfss.service;

import lombok.RequiredArgsConstructor;
import org.myfss.exception.MasterNotFoundException;
import org.myfss.model.Master;
import org.myfss.repository.MasterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterService {

    private final MasterRepository masterRepository;

    public List<Master> getAllMasters() {
        return masterRepository.findAll();
    }
}
