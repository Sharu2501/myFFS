package org.myfss.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.ApprenticeNotFoundException;
import org.myfss.exception.CompanyNotFoundException;
import org.myfss.model.*;
import org.myfss.model.enums.Major;
import org.springframework.stereotype.Service;
import org.myfss.repository.ApprenticeRepository;
import org.myfss.repository.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprenticeService {

    private final ApprenticeRepository apprenticeRepository;
    private final CompanyRepository companyRepository;

    public List<Apprentice> getAllApprentices() {
        return apprenticeRepository.findByMajorNot(Major.ALUMNI);
    }

    public List<Apprentice> getAllAlumni() {
        return apprenticeRepository.findByMajor(Major.ALUMNI);
    }

    public List<Apprentice> getAllApprenticesAndAlumni() {
        return apprenticeRepository.findAll();
    }

    public Apprentice getApprenticeById(Long Id) {
        return apprenticeRepository.findById(Id)
                .orElseThrow(() -> new ApprenticeNotFoundException(Id));
    }

    @Transactional
    public Apprentice createApprentice(Apprentice newApprentice) {
        newApprentice.setId(null);

        handleInputCompany(newApprentice);
        initializeNewApprentice(newApprentice);
        apprenticeRepository.save(newApprentice);

        return newApprentice;
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

    private void initializeNewApprentice(Apprentice newApprentice) {
        if (newApprentice.getMaster() != null) {
            Master master = newApprentice.getMaster();
            master.setId(null);
        }

        if (newApprentice.getMission() != null) {
            Mission mission = newApprentice.getMission();
            mission.setId(null);
        }

        newApprentice.setVisit(null);
        newApprentice.setEvaluation(null);
    }

    private void handleInputCompany(Apprentice newApprentice) {
        Company company = newApprentice.getCompany();

        if (company.getId() != null) {
            // Vérifier si l'entreprise existe déjà
            Company existingCompany = companyRepository.findById(company.getId())
                    .orElseThrow(() -> new CompanyNotFoundException(company.getId()));
            newApprentice.setCompany(existingCompany);
        } else if (isValidNewCompany(company)) {
            company.setId(null);
        }
    }

    private boolean isValidNewCompany(Company company) {
        return company.getSocialReason() != null && !company.getSocialReason().trim().isEmpty()
                && company.getAddress() != null && !company.getAddress().trim().isEmpty();
    }
}