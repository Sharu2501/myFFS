package org.myfss.controller.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.myfss.model.Mission;
import org.myfss.service.MissionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Missions")
@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionServiceImpl missionServiceImpl;

    @GetMapping
    public List<Mission> getAllMissions() {
        return missionServiceImpl.getAllMissions();
    }

    @GetMapping("/{id}")
    public Mission getMissionById(@PathVariable Long id) {
        return missionServiceImpl.getMissionById(id);
    }

    @PostMapping
    public ResponseEntity<Mission> createMission(@RequestBody Mission mission) {
        Mission created = missionServiceImpl.createMission(mission);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Mission> updateMission(@PathVariable Long id, @RequestBody Mission mission) {
        Mission updated = missionServiceImpl.updateMission(id, mission);
        return ResponseEntity.ok(updated);
    }
}
