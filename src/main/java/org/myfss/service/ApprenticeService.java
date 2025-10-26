package org.myfss.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.ApprenticeNotFoundException;
import org.myfss.exception.InvalidApprenticeDataException;
import org.myfss.model.Apprentice;
import org.myfss.dto.ApprenticeUpdateDTO;
import org.myfss.model.enums.Major;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.myfss.repository.ApprenticeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprenticeService {

    private final ApprenticeRepository apprenticeRepository;

    public List<Apprentice> getAllApprentices() {
        return apprenticeRepository.findByMajorNot(Major.ALUMNI);
    }

    public List<Apprentice> getAllApprenticesAndAlumni() {
        return apprenticeRepository.findAll();
    }

    public Apprentice getApprenticeById(Long id) {
        return apprenticeRepository.findById(id)
                .orElseThrow(() -> new ApprenticeNotFoundException(id));
    }

    @Transactional
    public Apprentice createApprentice(Apprentice newApprentice) {
        if (newApprentice == null) {
            throw new InvalidApprenticeDataException("Données de l'apprenti invalides.");
        }
        newApprentice.setId(null);
        return apprenticeRepository.save(newApprentice);
    }

    @Transactional
    public Apprentice updateApprentice(Long id, ApprenticeUpdateDTO dto) {
        if (dto == null) {
            throw new InvalidApprenticeDataException("Données de mise à jour invalides.");
        }
        Apprentice existing = getApprenticeById(id);
        BeanUtils.copyProperties(dto, existing);
        return apprenticeRepository.save(existing);
    }

    @Transactional
    public void createNewAcademicYear() {
        List<Apprentice> allApprentices = getAllApprentices();

        for (Apprentice apprentice : allApprentices) {
            apprentice.setMajor(apprentice.getMajor().next());
        }

        apprenticeRepository.saveAll(allApprentices);
    }

    public List<Apprentice> searchApprentices(String name, String company, String missionKeyword, String academicYear) {
        return apprenticeRepository.searchApprentices(name, company, missionKeyword, academicYear);
    }
}
