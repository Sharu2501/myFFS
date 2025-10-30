package org.myfss.service;

import com.opencsv.CSVReader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.ApprenticeExceptions.*;
import org.myfss.model.*;
import org.myfss.model.enums.Format;
import org.myfss.model.enums.Major;
import org.myfss.repository.ApprenticeRepository;
import org.myfss.util.ApprenticeCSVParser;
import org.myfss.util.ValidationUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApprenticeService {

    private final ApprenticeRepository apprenticeRepository;

    private final MasterService masterService;
    private final CompanyService companyService;
    private final MissionService missionService;
    private final OralService oralService;
    private final ReportService reportService;
    private final VisitService visitService;
    private final EvaluationService evaluationService;

    public List<Apprentice> getAllApprentices() {
        return apprenticeRepository.findByMajorNot(Major.ALUMNI);
    }

    public List<Apprentice> getAllAlumni() {
        return apprenticeRepository.findByMajor(Major.ALUMNI);
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
        validateApprenticeRequiredFields(newApprentice);
        initializeSubobject(newApprentice);
        newApprentice.setId(null);

        return apprenticeRepository.save(newApprentice);
    }

    @Transactional
    public Apprentice updateApprentice(Long id, Apprentice updatedApprentice) {
        Apprentice existingApprentice = getApprenticeById(id);

        existingApprentice.setFirstName(updatedApprentice.getFirstName());
        existingApprentice.setLastName(updatedApprentice.getLastName());
        existingApprentice.setEmail(updatedApprentice.getEmail());
        existingApprentice.setPhoneNumber(updatedApprentice.getPhoneNumber());
        existingApprentice.setAcademicYear(updatedApprentice.getAcademicYear());
        existingApprentice.setProgram(updatedApprentice.getProgram());
        existingApprentice.setMajor(updatedApprentice.getMajor());
        existingApprentice.setComments(updatedApprentice.getComments());
        existingApprentice.setTutorFeedback(updatedApprentice.getTutorFeedback());

        if (updatedApprentice.getCompany() != null) {
            Company company = existingApprentice.getCompany();
            companyService.updateCompany(company.getId(), updatedApprentice.getCompany());
        }

        if (updatedApprentice.getMaster() != null) {
            Master master = updatedApprentice.getMaster();
            masterService.updateMaster(master.getId(), updatedApprentice.getMaster());
        }

        if (updatedApprentice.getMission() != null) {
            Mission mission = updatedApprentice.getMission();
            missionService.updateMission(mission.getId(), updatedApprentice.getMission());
        }

        if (updatedApprentice.getVisit() != null) {
            Visit visit = updatedApprentice.getVisit();
            visitService.updateVisit(visit.getId(), updatedApprentice.getVisit());
        }

        if (updatedApprentice.getEvaluation() != null) {
            Evaluation eval = existingApprentice.getEvaluation();
            if (updatedApprentice.getEvaluation().getOral() != null) {
                Oral oral = updatedApprentice.getEvaluation().getOral();
                if (oral.getId() == null) {
                    oral = oralService.updateOral(eval.getOral().getId(), oral);
                }
                eval.setOral(oral);
            }
            if (updatedApprentice.getEvaluation().getReport() != null) {
                Report report = updatedApprentice.getEvaluation().getReport();
                if (report.getId() == null) {
                    report = reportService.updateReport(eval.getReport().getId(), report);
                }
                eval.setReport(report);
            }

            evaluationService.updateEvaluation(eval.getId(), updatedApprentice.getEvaluation());
        }

        return apprenticeRepository.save(existingApprentice);
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

    private void validateApprenticeRequiredFields(Apprentice apprentice) {
        ValidationUtils.validateRequiredFields(
                Map.of(
                        "nom", apprentice.getLastName(),
                        "prénom", apprentice.getFirstName(),
                        "email", apprentice.getEmail(),
                        "numéro de téléphone", apprentice.getPhoneNumber(),
                        "programme", apprentice.getProgram(),
                        "année académique", apprentice.getAcademicYear(),
                        "spécialité", apprentice.getMajor(),
                        "entreprise", apprentice.getCompany(),
                        "maître d'apprentissage", apprentice.getMaster(),
                        "mission", apprentice.getMission()
                ),
                InvalidApprenticeDataException::new
        );

        ValidationUtils.checkConflicts(
                Map.of(
                        "L'email de l'apprenti existe déjà.", () -> apprenticeRepository.existsByEmail(apprentice.getEmail().trim()),
                        "Le numéro de téléphone de l'apprenti exisite déjà.", () -> apprenticeRepository.existsByPhoneNumber(apprentice.getPhoneNumber().trim()),
                        "Le nom et prénom de l'apprenti existent déjà.", () -> apprenticeRepository.existsByLastNameAndFirstName(
                                apprentice.getLastName().trim(), apprentice.getFirstName().trim()
                        )
                ),
                ApprenticeAlreadyExistsException::new
        );
    }

    private void initializeSubobject(Apprentice apprentice) {
        Company company = apprentice.getCompany();
        Company existingCompany = company.getId() != null ? companyService.getCompanyById(company.getId()) : companyService.createCompany(company);
        apprentice.setCompany(existingCompany);

        Master master = masterService.createMaster(apprentice.getMaster());
        apprentice.setMaster(master);

        Mission mission = missionService.createMission(apprentice.getMission());
        apprentice.setMission(mission);

        apprentice.setEvaluation(new Evaluation());
        apprentice.getEvaluation().setOral(new Oral());
        apprentice.getEvaluation().setReport(new Report());

        apprentice.setVisit(new Visit());

        apprentice.setComments(apprentice.getComments() != null ? apprentice.getComments() : "");
        apprentice.setTutorFeedback(apprentice.getTutorFeedback() != null ? apprentice.getTutorFeedback() : "");
    }

    @Transactional
    public int importApprenticesFromCSV(MultipartFile file) throws Exception {
        List<Apprentice> imported = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            boolean skipHeader = true;

            while ((line = reader.readNext()) != null) {
                if (skipHeader) { skipHeader = false; continue; }

                Apprentice apprentice = ApprenticeCSVParser.parseLine(line);
                createApprentice(apprentice);
                imported.add(apprentice);
            }
        }

        return imported.size();
    }
}