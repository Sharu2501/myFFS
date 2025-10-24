package faria.sasikumar.sylla.myfss.service;

import faria.sasikumar.sylla.myfss.service.exceptions.ApprentiNotFoundException;
import faria.sasikumar.sylla.myfss.model.Apprenti;
import faria.sasikumar.sylla.myfss.repository.ApprentiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ApprentiService {

    private final ApprentiRepository apprentiRepository;

    public ApprentiService(ApprentiRepository apprentiRepository) {
        this.apprentiRepository = apprentiRepository;
    }

    // --- Récupération de tous les apprentis ---
    public List<Apprenti> getAllApprentis() {
        log.debug("Récupération de la liste complète des apprentis");
        List<Apprenti> apprentis = apprentiRepository.findAll();
        log.info("Nombre total d'apprentis trouvés : {}", apprentis.size());
        return apprentis;
    }

    // --- Récupération des apprentis non archivés ---
    public List<Apprenti> getAllApprentisNoArchived() {
        log.debug("Récupération des apprentis non archivés");
        List<Apprenti> apprentis = apprentiRepository.findAll()
                .stream()
                .filter(apprenti -> !apprenti.isArchived())
                .toList();
        log.info("{} apprentis actifs trouvés", apprentis.size());
        return apprentis;
    }

    // --- Récupération d’un apprenti par ID ---
    public Apprenti getApprenti(Long id) {
        log.debug("Recherche de l'apprenti avec id = {}", id);
        return apprentiRepository.findById(id)
                .map(apprenti -> {
                    log.info("Apprenti trouvé : {} {}", apprenti.getPrenom(), apprenti.getNom());
                    return apprenti;
                })
                .orElseThrow(() -> {
                    log.warn("Apprenti introuvable avec id = {}", id);
                    return new ApprentiNotFoundException(id);
                });
    }

    // --- Création ou mise à jour ---
    @Transactional
    public Apprenti createOrUpdateApprenti(Apprenti apprenti) {
        boolean isNew = apprenti.getId() == null;
        log.debug("{} de l'apprenti : {}", isNew ? "Création" : "Mise à jour", apprenti);
        Apprenti saved = apprentiRepository.save(apprenti);
        log.info("Apprenti {} enregistré avec id = {}", isNew ? "créé" : "mis à jour", saved.getId());
        return saved;
    }

    // --- Suppression ---
    public void deleteApprenti(Long id) {
        log.debug("Suppression de l'apprenti avec id = {}", id);
        if (!apprentiRepository.existsById(id)) {
            log.error("Impossible de supprimer : apprenti avec id = {} introuvable", id);
            throw new ApprentiNotFoundException(id);
        }
        apprentiRepository.deleteById(id);
        log.info("Apprenti avec id = {} supprimé avec succès", id);
    }

    // --- Recherche par nom ---
    public List<Apprenti> searchByNom(String nom) {
        log.debug("Recherche des apprentis par nom contenant : {}", nom);
        List<Apprenti> result = apprentiRepository.findByNomContainingIgnoreCase(nom);
        log.info("{} apprentis trouvés contenant '{}'", result.size(), nom);
        return result;
    }

    // --- Nouvelle année académique ---
    @Transactional
    public void newAcademiqueYear() {
        log.info("Début du traitement de la nouvelle année académique");
        getAllApprentis().forEach(apprenti -> {
            apprenti.addYear();
            apprentiRepository.save(apprenti);
            log.debug("Année incrémentée pour l'apprenti id = {}", apprenti.getId());
        });
        log.info("Nouvelle année académique appliquée à tous les apprentis");
    }

    // --- Archivage ---
    @Transactional
    public void archive(Long id) {
        log.debug("Archivage de l'apprenti avec id = {}", id);
        Apprenti apprenti = apprentiRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Impossible d'archiver : apprenti id = {} introuvable", id);
                    return new ApprentiNotFoundException(id);
                });

        apprenti.setArchived(true);
        apprentiRepository.save(apprenti);
        log.info("Apprenti avec id = {} archivé avec succès", id);
    }
}
