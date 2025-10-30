package org.myfss.service;

import org.myfss.model.Mission;
import java.util.List;

public interface MissionService {

    List<Mission> getAllMissions();

    Mission getMissionById(Long id);

    Mission createMission(Mission newMission);

    Mission updateMission(Long id, Mission updatedMission);
}