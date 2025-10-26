package org.myffs.controller;

import lombok.RequiredArgsConstructor;
import org.myffs.model.Apprentice;
import org.myffs.dto.ApprenticeUpdateDTO;
import org.myffs.service.ApprenticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apprentices")
@RequiredArgsConstructor
public class ApprenticeController {

    private final ApprenticeService apprenticeService;

    @GetMapping("/active")
    public List<Apprentice> getActiveApprentices() {
        return apprenticeService.getAllApprentices();
    }

    @GetMapping("/all")
    public List<Apprentice> getAllApprenticesAndAlumni() {
        return apprenticeService.getAllApprenticesAndAlumni();
    }

    @GetMapping("/{id}")
    public Apprentice getApprenticeById(@PathVariable Long id) {
        return apprenticeService.getApprenticeById(id);
    }

    @PostMapping()
    public ResponseEntity<Apprentice> createApprentice(@RequestBody Apprentice newApprentice) {
        Apprentice created = apprenticeService.createApprentice(newApprentice);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public Apprentice updateApprentice(@PathVariable Long id, @RequestBody ApprenticeUpdateDTO dto) {
        return apprenticeService.updateApprentice(id, dto);
    }

    @PostMapping("/new-academic-year")
    public ResponseEntity<String> createNewAcademicYear() {
        apprenticeService.createNewAcademicYear();
        return ResponseEntity.ok("Nouvelle année académique créée avec succès");
    }

    @GetMapping("/search")
    public List<Apprentice> searchApprentices(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String missionKeyword,
            @RequestParam(required = false) String academicYear
    ) {
        return apprenticeService.searchApprentices(name, company, missionKeyword, academicYear);
    }
}
