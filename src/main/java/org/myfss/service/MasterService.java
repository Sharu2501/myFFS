package org.myfss.service;

import jakarta.transaction.Transactional;
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

    public Master getMasterById(Long Id) {
        return masterRepository.findById(Id)
                .orElseThrow(() -> new MasterNotFoundException(Id));
    }

    @Transactional
    public Master createMaster(Master newMaster) {
        newMaster.setId(null);
        return masterRepository.save(newMaster);
    }
}
