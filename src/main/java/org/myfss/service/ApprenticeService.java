package org.myfss.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.ApprenticeNotFoundException;
import org.myfss.exception.InvalidApprenticeDataException;
import org.myfss.model.Apprentice;
import org.myfss.dto.ApprenticeUpdateDTO;
import org.myfss.model.Company;
import org.myfss.model.Master;
import org.myfss.model.enums.Major;
import org.myfss.repository.CompanyRepository;
import org.myfss.repository.MasterRepository;
import org.springframework.stereotype.Service;
import org.myfss.repository.ApprenticeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprenticeService {

    private final ApprenticeRepository apprenticeRepository;
    private final MasterRepository masterRepository;
    private final CompanyRepository companyRepository;

    public List<Apprentice> getAllApprentices() {
        return apprenticeRepository.findByMajorNot(Major.ALUMNI);
    }

    public List<Apprentice> getAllApprenticesAndAlumni() {
        return apprenticeRepository.findAll();
    }

    public List<Apprentice> getAllAlumni() {
        return apprenticeRepository.findAll()
                .stream()
                .filter(a -> a.getMajor() == Major.ALUMNI)
                .toList();
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

        // Champs simples
        existing.setProgram(dto.getProgram());
        existing.setAcademicYear(dto.getAcademicYear());
        existing.setMajor(dto.getMajor());
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setPhoneNumber(dto.getPhoneNumber());

        // Associations
        if (dto.getCompanyId() != null) {
            Company company = companyRepository.getCompanyById(dto.getCompanyId());
            existing.setCompany(company);
        } else {
            existing.setCompany(null);
        }

        if (dto.getMasterId() != null) {
            Master master = masterRepository.getMasterById(dto.getMasterId());
            existing.setMaster(master);
        } else {
            existing.setMaster(null);
        }

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
