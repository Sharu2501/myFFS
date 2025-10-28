package org.myfss.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.myfss.model.Master;
import org.myfss.service.MasterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Masters")
@RestController
@RequestMapping("/api/masters")
@RequiredArgsConstructor
public class MasterController {

    private final MasterService masterService;

    @GetMapping
    public List<Master> getAllMasters() {
        return masterService.getAllMasters();
    }

    @PostMapping
    public ResponseEntity<Master> createMaster(@RequestBody Master newMaster) {
        Master createdMaster = masterService.createMaster(newMaster);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMaster);
    }
}
