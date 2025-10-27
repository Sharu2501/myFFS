package org.myfss.service;

import lombok.RequiredArgsConstructor;
import org.myfss.model.Mission;
import org.myfss.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    public void saveMission(Mission mission) {
        missionRepository.save(mission);
    }
}
