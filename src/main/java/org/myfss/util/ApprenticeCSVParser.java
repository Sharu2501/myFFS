package org.myfss.util;

import org.myfss.model.*;
import org.myfss.model.enums.Format;
import org.myfss.model.enums.Major;

import java.time.LocalDate;

public class ApprenticeCSVParser {

    public static Apprentice parseLine(String[] line) {
        Apprentice apprentice = new Apprentice();
        apprentice.setProgram(line[0]);
        apprentice.setAcademicYear(line[1]);
        apprentice.setMajor(Major.valueOf(line[2]));
        apprentice.setLastName(line[3]);
        apprentice.setFirstName(line[4]);
        apprentice.setEmail(line[5].trim());
        apprentice.setPhoneNumber(line[6]);

        apprentice.setCompany(parseCompany(line));
        apprentice.setMaster(parseMaster(line));
        apprentice.setMission(parseMission(line));
        apprentice.setVisit(parseVisit(line));
        apprentice.setEvaluation(parseEvaluation(line));

        apprentice.setComments(line[26]);
        apprentice.setTutorFeedback(line[27]);

        return apprentice;
    }

    private static Company parseCompany(String[] line) {
        Company company = new Company();
        company.setSocialReason(line[7]);
        company.setAddress(line[8]);
        return company;
    }

    private static Master parseMaster(String[] line) {
        Master master = new Master();
        master.setLastName(line[9]);
        master.setFirstName(line[10]);
        master.setEmail(line[11]);
        master.setPhoneNumber(line[12]);
        master.setPosition(line[13]);
        return master;
    }

    private static Mission parseMission(String[] line) {
        Mission mission = new Mission();
        mission.setKeywords(line[14]);
        mission.setProfession(line[15]);
        mission.setComments(line[16]);
        return mission;
    }

    private static Visit parseVisit(String[] line) {
        Visit visit = new Visit();
        visit.setFormat(!line[18].isBlank() ? Format.valueOf(line[18].trim().toUpperCase()) : null);
        visit.setComments(line[19]);
        return visit;
    }

    private static Evaluation parseEvaluation(String[] line) {
        Evaluation evaluation = new Evaluation();

        Report report = new Report();
        report.setTheme(line[20]);
        if (!line[21].isBlank()) report.setGrade(Double.parseDouble(line[21]));
        report.setComments(line[22]);
        evaluation.setReport(report);

        Oral oral = new Oral();
        if (!line[23].isBlank()) oral.setDate(LocalDate.parse(line[23]));
        if (!line[24].isBlank()) oral.setGrade(Double.parseDouble(line[24]));
        oral.setComments(line[25]);
        evaluation.setOral(oral);

        return evaluation;
    }
}