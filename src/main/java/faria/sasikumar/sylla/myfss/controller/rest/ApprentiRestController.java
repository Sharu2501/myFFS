package faria.sasikumar.sylla.myfss.controller.rest;

import faria.sasikumar.sylla.myfss.model.Apprenti;
import faria.sasikumar.sylla.myfss.repository.*;
import faria.sasikumar.sylla.myfss.service.ApprentiService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Contrôleur REST pour la gestion des Apprentis.
 * Respecte les conventions RESTful :
 *  - GET /rest/apprenti → liste
 *  - GET /rest/apprenti/{id} → détail
 *  - POST /rest/apprenti → création
 *  - PUT /rest/apprenti/{id} → mise à jour
 *  - DELETE /rest/apprenti/{id} → suppression
 */
@RestController
@RequestMapping("/rest/apprenti")
public class ApprentiRestController {

    private final ApprentiService apprentiService;

    public ApprentiRestController(ApprentiService apprentiService) {
        this.apprentiService = apprentiService;
    }

    // --- READ : Liste des apprentis ---
    @GetMapping
    public ResponseEntity<List<Apprenti>> getAllApprentis() {
        List<Apprenti> apprentis = apprentiService.getAllApprentisNoArchived();
        return ResponseEntity.ok(apprentis);
    }

    // --- READ : Détails d’un apprenti ---
    @GetMapping("/{id}")
    public ResponseEntity<Apprenti> getApprentiById(@PathVariable Long id) {
        Apprenti apprenti = apprentiService.getApprenti(id);
        return ResponseEntity.ok(apprenti);
    }

    // --- CREATE : Créer un apprenti ---
    @PostMapping
    public ResponseEntity<Apprenti> createApprenti(@Valid @RequestBody Apprenti apprenti) {
        Apprenti saved = apprentiService.createOrUpdateApprenti(apprenti);
        URI location = URI.create("/rest/apprenti/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

    // --- UPDATE : Mise à jour d’un apprenti ---
    @PutMapping("/{id}")
    public ResponseEntity<Apprenti> updateApprenti(@PathVariable Long id, @Valid @RequestBody Apprenti apprenti) {
        apprenti.setId(id);
        Apprenti updated = apprentiService.createOrUpdateApprenti(apprenti);
        return ResponseEntity.ok(updated);
    }

    // --- DELETE : Supprimer un apprenti ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApprenti(@PathVariable Long id) {
        apprentiService.deleteApprenti(id);
        return ResponseEntity.noContent().build();
    }

    // --- ARCHIVE : Archiver un apprenti ---
    @PostMapping("/{id}/archive")
    public ResponseEntity<Void> archiveApprenti(@PathVariable Long id) {
        apprentiService.archive(id);
        return ResponseEntity.ok().build();
    }

    // --- ACTION SPÉCIALE : nouvelle année académique ---
    @PostMapping("/new-year")
    public ResponseEntity<Void> startNewAcademicYear() {
        apprentiService.newAcademiqueYear();
        return ResponseEntity.ok().build();
    }
}
