package org.myfss.service;

import org.myfss.model.Apprentice;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApprenticeService {

    List<Apprentice> getAllApprentices();

    List<Apprentice> getAllAlumni();

    List<Apprentice> getAllApprenticesAndAlumni();

    Apprentice getApprenticeById(Long id);

    Apprentice createApprentice(Apprentice newApprentice);

    Apprentice updateApprentice(Long id, Apprentice updatedApprentice);

    void createNewAcademicYear();

    List<Apprentice> searchApprentices(String name, String company, String missionKeyword, String academicYear);

    int importApprenticesFromCSV(MultipartFile file) throws Exception;
}