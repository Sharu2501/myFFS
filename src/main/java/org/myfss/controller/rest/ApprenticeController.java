package org.myfss.controller.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.myfss.model.Apprentice;
import org.myfss.service.ApprenticeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Apprentices")
@RestController
@RequestMapping("/api/apprentices")
@RequiredArgsConstructor
public class ApprenticeController {

    private final ApprenticeServiceImpl apprenticeServiceImpl;

    @GetMapping("/active")
    public List<Apprentice> getAllApprentices() {
        return apprenticeServiceImpl.getAllApprentices();
    }

    @GetMapping("/alumni")
    public List<Apprentice> getAllAlumni() {
        return apprenticeServiceImpl.getAllAlumni();
    }

    @GetMapping("/all")
    public List<Apprentice> getAllApprenticesAndAlumni() {
        return apprenticeServiceImpl.getAllApprenticesAndAlumni();
    }

    @GetMapping("/{id}")
    public Apprentice getApprenticeById(@PathVariable Long id) {
        return apprenticeServiceImpl.getApprenticeById(id);
    }

    @PostMapping
    public ResponseEntity<Apprentice> createApprentice(@RequestBody Apprentice apprentice) {
        Apprentice created = apprenticeServiceImpl.createApprentice(apprentice);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/new-academic-year")
    public ResponseEntity<String> createNewAcademicYear() {
        apprenticeServiceImpl.createNewAcademicYear();
        return ResponseEntity.ok("Nouvelle année académique créée avec succès");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Apprentice> updateApprentice(@PathVariable Long id, @RequestBody Apprentice apprentice) {
        Apprentice updated = apprenticeServiceImpl.updateApprentice(id, apprentice);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/search")
    public List<Apprentice> searchApprentices(@RequestParam(required = false) String name, @RequestParam(required = false) String company, @RequestParam(required = false) String missionKeyword, @RequestParam(required = false) String academicYear) {
        return apprenticeServiceImpl.searchApprentices(name, company, missionKeyword, academicYear);
    }
}
