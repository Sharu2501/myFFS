package org.myfss.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.ApprenticeNotFoundException;
import org.myfss.exception.CompanyNotFoundException;
import org.myfss.exception.MissingReportForOralException;
import org.myfss.exception.MissingVisitForEvaluationException;
import org.myfss.model.*;
import org.myfss.model.enums.Major;
import org.myfss.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprenticeService {

    private final ApprenticeRepository apprenticeRepository;
    private final CompanyRepository companyRepository;
    private final MasterRepository masterRepository;
    private final MissionRepository missionRepository;
    private final VisitRepository visitRepository;
    private final EvaluationRepository evaluationRepository;
    private final OralRepository oralRepository;
    private final ReportRepository reportRepository;

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

        if (newApprentice.getCompanyId() != null) {
            Company existingCompany = companyRepository.findById(newApprentice.getCompanyId())
                    .orElseThrow(() -> new CompanyNotFoundException(newApprentice.getCompanyId()));
            newApprentice.setCompany(existingCompany);
        } else if (newApprentice.getCompany() != null && isValidNewCompany(newApprentice.getCompany())) {
            newApprentice.getCompany().setId(null);
        } else {
            throw new IllegalArgumentException("Une entreprise doit être sélectionnée ou créée.");
        }

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

    @Transactional
    public void updateApprentice(Long id, Apprentice updatedApprentice) {
        Apprentice existingApprentice = apprenticeRepository.findById(id)
                .orElseThrow(() -> new ApprenticeNotFoundException(id));

        updateBasicInput(updatedApprentice, existingApprentice);

        handleUpdateCompany(updatedApprentice, existingApprentice);
        handleUpdateMaster(updatedApprentice, existingApprentice);
        handleUpdateMission(updatedApprentice, existingApprentice);
        handleUpdateVisit(updatedApprentice, existingApprentice);
        handleUpdateOral(updatedApprentice, existingApprentice);
        handleUpdateReport(updatedApprentice, existingApprentice);
        handleUpdateEvaluation(updatedApprentice, existingApprentice);
        apprenticeRepository.save(existingApprentice);
    }

    private static void updateBasicInput(Apprentice updatedApprentice, Apprentice existingApprentice) {
        existingApprentice.setFirstName(updatedApprentice.getFirstName());
        existingApprentice.setLastName(updatedApprentice.getLastName());
        existingApprentice.setEmail(updatedApprentice.getEmail());
        existingApprentice.setPhoneNumber(updatedApprentice.getPhoneNumber());
        existingApprentice.setProgram(updatedApprentice.getProgram());
        existingApprentice.setAcademicYear(updatedApprentice.getAcademicYear());
        existingApprentice.setMajor(updatedApprentice.getMajor());
        existingApprentice.setComments(updatedApprentice.getComments());
        existingApprentice.setTutorFeedback(updatedApprentice.getTutorFeedback());
    }

    private void handleUpdateCompany(Apprentice updatedApprentice, Apprentice existingApprentice) {
        Company updatedCompany = updatedApprentice.getCompany();
        if (updatedCompany == null) return;

        Company existingCompany = existingApprentice.getCompany();

        if (existingCompany == null) {
            if (isValidNewCompany(updatedCompany)) {
                companyRepository.save(updatedCompany);
                existingApprentice.setCompany(updatedCompany);
            }
        } else {
            existingCompany.setSocialReason(updatedCompany.getSocialReason());
            existingCompany.setAddress(updatedCompany.getAddress());
            existingCompany.setAccessInformation(updatedCompany.getAccessInformation());
        }
    }

    private void handleUpdateMaster(Apprentice updatedApprentice, Apprentice existingApprentice) {
        Master updatedMaster = updatedApprentice.getMaster();
        if (updatedMaster == null) return;

        Master existingMaster = existingApprentice.getMaster();

        if (existingMaster == null) {
            if (isValidNewMaster(updatedMaster)) {
                masterRepository.save(updatedMaster);
                existingApprentice.setMaster(updatedMaster);
            }
        } else {
            existingMaster.setFirstName(updatedMaster.getFirstName());
            existingMaster.setLastName(updatedMaster.getLastName());
            existingMaster.setEmail(updatedMaster.getEmail());
            existingMaster.setPhoneNumber(updatedMaster.getPhoneNumber());
            existingMaster.setPosition(updatedMaster.getPosition());
        }
    }

    private void handleUpdateMission(Apprentice updatedApprentice, Apprentice existingApprentice) {
        Mission updatedMission = updatedApprentice.getMission();
        if (updatedMission == null) return;

        Mission existingMission = existingApprentice.getMission();

        if (existingMission == null) {
            if (isValidNewMission(updatedMission)) {
                missionRepository.save(updatedMission);
                existingApprentice.setMission(updatedMission);
            }
        } else {
            existingMission.setKeywords(updatedMission.getKeywords());
            existingMission.setProfession(updatedMission.getProfession());
            existingMission.setComments(updatedMission.getComments());
        }
    }

    private void handleUpdateVisit(Apprentice updatedApprentice, Apprentice existingApprentice) {
        Visit updatedVisit = updatedApprentice.getVisit();

        if (updatedVisit == null || (updatedVisit.getDate() == null && updatedVisit.getFormat() == null)) {
            return;
        }

        Visit existingVisit = existingApprentice.getVisit();

        if (existingVisit == null) {
            visitRepository.save(updatedVisit);
            existingApprentice.setVisit(updatedVisit);
        } else {
            existingVisit.setDate(updatedVisit.getDate());
            existingVisit.setFormat(updatedVisit.getFormat());
            existingVisit.setComments(updatedVisit.getComments());
        }
    }

    private void handleUpdateEvaluation(Apprentice updatedApprentice, Apprentice existingApprentice) {
        Evaluation updatedEvaluation = updatedApprentice.getEvaluation();

        if (updatedEvaluation == null ||
                ((updatedEvaluation.getReport() == null || isEmptyReport(updatedEvaluation.getReport())) &&
                        (updatedEvaluation.getOral() == null || isEmptyOral(updatedEvaluation.getOral())))) {
            return;
        }

        if (existingApprentice.getVisit() == null) {
            throw new MissingVisitForEvaluationException();
        }

        Evaluation existingEvaluation = existingApprentice.getEvaluation();

        if (existingEvaluation == null) {
            Evaluation newEval = new Evaluation();

            if (updatedEvaluation.getReport() != null && isValidNewReport(updatedEvaluation.getReport())) {
                reportRepository.save(updatedEvaluation.getReport());
                newEval.setReport(updatedEvaluation.getReport());
            }

            if (updatedEvaluation.getOral() != null && newEval.getReport() != null &&
                    isValidNewOral(updatedEvaluation.getOral())) {
                oralRepository.save(updatedEvaluation.getOral());
                newEval.setOral(updatedEvaluation.getOral());
            }

            if (newEval.getReport() != null || newEval.getOral() != null) {
                evaluationRepository.save(newEval);
                existingApprentice.setEvaluation(newEval);
            }
        } else {
            handleUpdateReport(updatedApprentice, existingApprentice);
            handleUpdateOral(updatedApprentice, existingApprentice);
        }
    }

    private void handleUpdateOral(Apprentice updatedApprentice, Apprentice existingApprentice) {
        if (updatedApprentice.getEvaluation() == null) return;

        Oral updatedOral = updatedApprentice.getEvaluation().getOral();
        if (updatedOral == null || isEmptyOral(updatedOral)) return;

        Evaluation existingEval = existingApprentice.getEvaluation();
        if (existingEval == null) return;

        Report existingReport = existingEval.getReport();
        if (existingReport == null) {
            throw new MissingReportForOralException();
        }

        Oral existingOral = existingEval.getOral();

        if (existingOral == null) {
            if (isValidNewOral(updatedOral)) {
                oralRepository.save(updatedOral);
                existingEval.setOral(updatedOral);
            }
        } else {
            existingOral.setDate(updatedOral.getDate());
            existingOral.setGrade(updatedOral.getGrade());
            existingOral.setComments(updatedOral.getComments());
        }
    }

    private void handleUpdateReport(Apprentice updatedApprentice, Apprentice existingApprentice) {
        if (updatedApprentice.getEvaluation() == null) return;

        Report updatedReport = updatedApprentice.getEvaluation().getReport();
        if (updatedReport == null) return;

        Evaluation existingEval = existingApprentice.getEvaluation();
        if (existingEval == null) return;

        Report existingReport = existingEval.getReport();

        if (existingReport == null) {
            if (isValidNewReport(updatedReport)) {
                reportRepository.save(updatedReport);
                existingEval.setReport(updatedReport);
            }
        } else {
            existingReport.setTheme(updatedReport.getTheme());
            existingReport.setGrade(updatedReport.getGrade());
            existingReport.setComments(updatedReport.getComments());
        }
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

    private boolean isValidNewCompany(Company company) {
        if (company == null) return false;

        boolean socialReasonOk = company.getSocialReason() != null &&
                !company.getSocialReason().trim().isEmpty() &&
                company.getSocialReason().matches("^[A-Za-z0-9À-ÖØ-öø-ÿ .,\\-]+$");

        boolean addressOk = company.getAddress() != null &&
                !company.getAddress().trim().isEmpty() &&
                company.getAddress().matches("^[A-Za-z0-9À-ÖØ-öø-ÿ .,\\-]+$");

        return socialReasonOk && addressOk;
    }

    private boolean isValidNewMaster(Master master) {
        return master.getFirstName() != null && !master.getFirstName().trim().isEmpty()
                && master.getLastName() != null && !master.getLastName().trim().isEmpty()
                && master.getEmail() != null && !master.getEmail().trim().isEmpty()
                && master.getPhoneNumber() != null && !master.getPhoneNumber().trim().isEmpty()
                && master.getPosition() != null && !master.getPosition().trim().isEmpty();
    }

    private boolean isValidNewMission(Mission mission) {
        return mission.getKeywords() != null && !mission.getKeywords().trim().isEmpty()
                && mission.getProfession() != null && !mission.getProfession().trim().isEmpty();
    }

    private boolean isValidNewOral(Oral oral) {
        return oral != null && (oral.getDate() != null || oral.getGrade() != null);
    }

    private boolean isValidNewReport(Report report) {
        return report != null && (report.getTheme() != null || report.getGrade() != null);
    }

    private boolean isEmptyReport(Report report) {
        return (report.getTheme() == null || report.getTheme().trim().isEmpty()) &&
                report.getGrade() == null &&
                (report.getComments() == null || report.getComments().trim().isEmpty());
    }

    private boolean isEmptyOral(Oral oral) {
        return oral.getDate() == null &&
                oral.getGrade() == null &&
                (oral.getComments() == null || oral.getComments().trim().isEmpty());
    }
}