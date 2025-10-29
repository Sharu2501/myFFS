package org.myfss.controller.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.myfss.model.Apprentice;
import org.myfss.service.ApprenticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Apprentices")
@RestController
@RequestMapping("/api/apprentices")
@RequiredArgsConstructor
public class ApprenticeController {

    private final ApprenticeService apprenticeService;

    @GetMapping("/active")
    public List<Apprentice> getAllApprentices() {
        return apprenticeService.getAllApprentices();
    }

    @GetMapping("/alumni")
    public List<Apprentice> getAllAlumni() {
        return apprenticeService.getAllAlumni();
    }

    @GetMapping("/all")
    public List<Apprentice> getAllApprenticesAndAlumni() {
        return apprenticeService.getAllApprenticesAndAlumni();
    }

    @GetMapping("/{id}")
    public Apprentice getApprenticeById(@PathVariable Long id) {
        return apprenticeService.getApprenticeById(id);
    }

    @PostMapping
    public ResponseEntity<Apprentice> createApprentice(@RequestBody Apprentice apprentice) {
        Apprentice created = apprenticeService.createApprentice(apprentice);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/new-academic-year")
    public ResponseEntity<String> createNewAcademicYear() {
        apprenticeService.createNewAcademicYear();
        return ResponseEntity.ok("Nouvelle année académique créée avec succès");
    }

    @GetMapping("/search")
    public List<Apprentice> searchApprentices(@RequestParam(required = false) String name, @RequestParam(required = false) String company, @RequestParam(required = false) String missionKeyword, @RequestParam(required = false) String academicYear) {
        return apprenticeService.searchApprentices(name, company, missionKeyword, academicYear);
    }
}
