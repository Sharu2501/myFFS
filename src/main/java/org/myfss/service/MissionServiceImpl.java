package org.myfss.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.MissionExceptions.*;
import org.myfss.model.Mission;
import org.myfss.repository.MissionRepository;
import org.myfss.util.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    public Mission getMissionById(Long id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new MissionNotFoundException(id));
    }

    @Transactional
    public Mission createMission(Mission newMission) {
        validateMissionRequiredFields(newMission);
        newMission.setId(null);

        return missionRepository.save(newMission);
    }

    @Transactional
    public Mission updateMission(Long id, Mission updatedMission) {
        Mission existingMission = getMissionById(id);

        applyChanges(existingMission, updatedMission);
        validateMissionRequiredFields(existingMission);

        return missionRepository.save(existingMission);
    }

    private void validateMissionRequiredFields(Mission mission) {
        ValidationUtils.validateRequiredFields(
                Map.of(
                        "mots-clés", mission.getKeywords(),
                        "profession", mission.getProfession()
                ),
                InvalidMissionDataException::new
        );
    }

    private void applyChanges(Mission targetMission, Mission sourceMission) {
        if (!sourceMission.getKeywords().equals(targetMission.getKeywords())) {
            targetMission.setKeywords(sourceMission.getKeywords());
        }

        if (!sourceMission.getProfession().equals(targetMission.getProfession())) {
            targetMission.setProfession(sourceMission.getProfession());
        }

        if (sourceMission.getComments() != null &&
                !sourceMission.getComments().equals(targetMission.getComments())) {
            targetMission.setComments(sourceMission.getComments());
        }
    }
}
