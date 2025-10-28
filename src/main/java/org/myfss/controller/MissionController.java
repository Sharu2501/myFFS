package org.myfss.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.myfss.model.Mission;
import org.myfss.service.MissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Missions")
@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public List<Mission> getAllMissions() {
        return missionService.getAllMissions();
    }

    @PostMapping
    public ResponseEntity<Mission> createMission(@RequestBody Mission newMission) {
        Mission createdMission = missionService.createMission(newMission);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMission);
    }
}
