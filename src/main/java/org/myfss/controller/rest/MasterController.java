package org.myfss.controller.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.myfss.model.Company;
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

    @GetMapping("/{id}")
    public Master getMasterById(@PathVariable Long id) {
        return masterService.getMasterById(id);
    }

    @PostMapping
    public ResponseEntity<Master> createMaster(@RequestBody Master master) {
        Master created = masterService.createMaster(master);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Master> updateMaster(@PathVariable Long id, @RequestBody Master master) {
        Master updated = masterService.updateMaster(id, master);
        return ResponseEntity.ok(updated);
    }
}
