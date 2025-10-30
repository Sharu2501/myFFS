package org.myfss.model.mapper;

import org.myfss.model.*;
import org.myfss.model.dto.*;
import org.myfss.model.enums.Format;

public class ApprenticeMapper {

    public static ApprenticeDTO toDTO(Apprentice entity) {
        if (entity == null) return null;

        return ApprenticeDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .program(entity.getProgram())
                .academicYear(entity.getAcademicYear())
                .major(entity.getMajor())
                .company(entity.getCompany() != null ?
                        CompanyDTO.builder()
                                .id(entity.getCompany().getId())
                                .socialReason(entity.getCompany().getSocialReason())
                                .address(entity.getCompany().getAddress())
                                .accessInformation(entity.getCompany().getAccessInformation())
                                .build() : null)
                .master(entity.getMaster() != null ?
                        MasterDTO.builder()
                                .id(entity.getMaster().getId())
                                .lastName(entity.getMaster().getLastName())
                                .firstName(entity.getMaster().getFirstName())
                                .position(entity.getMaster().getPosition())
                                .email(entity.getMaster().getEmail())
                                .phoneNumber(entity.getMaster().getPhoneNumber())
                                .comments(entity.getMaster().getComments())
                                .build() : null)
                .mission(entity.getMission() != null ?
                        MissionDTO.builder()
                                .id(entity.getMission().getId())
                                .keywords(entity.getMission().getKeywords())
                                .profession(entity.getMission().getProfession())
                                .comments(entity.getMission().getComments())
                                .build() : null)
                .visit(entity.getVisit() != null ?
                        VisitDTO.builder()
                                .id(entity.getVisit().getId())
                                .date(entity.getVisit().getDate())
                                .format(entity.getVisit().getFormat().name())
                                .comments(entity.getVisit().getComments())
                                .build() : null)
                .evaluation(entity.getEvaluation() != null ?
                        EvaluationDTO.builder()
                                .id(entity.getEvaluation().getId())
                                .oral(entity.getEvaluation().getOral() != null ?
                                        OralDTO.builder()
                                                .id(entity.getEvaluation().getOral().getId())
                                                .date(entity.getEvaluation().getOral().getDate())
                                                .grade(entity.getEvaluation().getOral().getGrade())
                                                .comments(entity.getEvaluation().getOral().getComments())
                                                .build() : null)
                                .report(entity.getEvaluation().getReport() != null ?
                                        ReportDTO.builder()
                                                .id(entity.getEvaluation().getReport().getId())
                                                .theme(entity.getEvaluation().getReport().getTheme())
                                                .grade(entity.getEvaluation().getReport().getGrade())
                                                .comments(entity.getEvaluation().getReport().getComments())
                                                .build() : null)
                                .build() : null)
                .comments(entity.getComments())
                .tutorFeedback(entity.getTutorFeedback())
                .build();
    }

    public static Apprentice toEntity(ApprenticeDTO dto) {
        if (dto == null) return null;

        Apprentice entity = Apprentice.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .program(dto.getProgram())
                .academicYear(dto.getAcademicYear())
                .major(dto.getMajor())
                .comments(dto.getComments())
                .tutorFeedback(dto.getTutorFeedback())
                .build();

        if (dto.getCompany() != null) {
            Company company = new Company();
            company.setId(dto.getCompany().getId());
            company.setSocialReason(dto.getCompany().getSocialReason());
            company.setAddress(dto.getCompany().getAddress());
            company.setAccessInformation(dto.getCompany().getAccessInformation());
            entity.setCompany(company);
        }

        if (dto.getMaster() != null) {
            Master master = new Master();
            master.setId(dto.getMaster().getId());
            master.setLastName(dto.getMaster().getLastName());
            master.setFirstName(dto.getMaster().getFirstName());
            master.setPosition(dto.getMaster().getPosition());
            master.setEmail(dto.getMaster().getEmail());
            master.setPhoneNumber(dto.getMaster().getPhoneNumber());
            master.setComments(dto.getMaster().getComments());
            entity.setMaster(master);
        }

        if (dto.getMission() != null) {
            Mission mission = new Mission();
            mission.setId(dto.getMission().getId());
            mission.setKeywords(dto.getMission().getKeywords());
            mission.setProfession(dto.getMission().getProfession());
            mission.setComments(dto.getMission().getComments());
            entity.setMission(mission);
        }

        if (dto.getVisit() != null) {
            Visit visit = new Visit();
            visit.setId(dto.getVisit().getId());
            visit.setDate(dto.getVisit().getDate());
            visit.setFormat(dto.getVisit().getFormat() != null ? Enum.valueOf(Format.class, dto.getVisit().getFormat()) : null);
            visit.setComments(dto.getVisit().getComments());
            entity.setVisit(visit);
        }

        if (dto.getEvaluation() != null) {
            Evaluation evaluation = new Evaluation();
            evaluation.setId(dto.getEvaluation().getId());

            if (dto.getEvaluation().getOral() != null) {
                Oral oral = new Oral();
                oral.setId(dto.getEvaluation().getOral().getId());
                oral.setDate(dto.getEvaluation().getOral().getDate());
                oral.setGrade(dto.getEvaluation().getOral().getGrade());
                oral.setComments(dto.getEvaluation().getOral().getComments());
                evaluation.setOral(oral);
            }

            if (dto.getEvaluation().getReport() != null) {
                Report report = new Report();
                report.setId(dto.getEvaluation().getReport().getId());
                report.setTheme(dto.getEvaluation().getReport().getTheme());
                report.setGrade(dto.getEvaluation().getReport().getGrade());
                report.setComments(dto.getEvaluation().getReport().getComments());
                evaluation.setReport(report);
            }

            entity.setEvaluation(evaluation);
        }

        return entity;
    }
}
