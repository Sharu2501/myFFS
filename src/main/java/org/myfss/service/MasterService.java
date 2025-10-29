package org.myfss.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.MasterExceptions.*;
import org.myfss.model.Master;
import org.myfss.repository.MasterRepository;
import org.myfss.util.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MasterService {

    private final MasterRepository masterRepository;

    public List<Master> getAllMasters() {
        return masterRepository.findAll();
    }

    public Master getMasterById(Long id) {
        return masterRepository.findById(id)
                .orElseThrow(() -> new MasterNotFoundException(id));
    }

    @Transactional
    public Master createMaster(Master newMaster) {
        validateMasterRequiredFields(newMaster);
        newMaster.setId(null);
        return masterRepository.save(newMaster);
    }

    @Transactional
    public Master updateMaster(Long id, Master updatedMaster) {
        Master existing = getMasterById(id);

        validateMasterRequiredFields(updatedMaster);
        applyChanges(existing, updatedMaster);

        return masterRepository.save(existing);
    }

    private void validateMasterRequiredFields(Master master) {
        ValidationUtils.validateRequiredFields(
                Map.of(
                        "nom", master.getLastName(),
                        "prénom", master.getFirstName(),
                        "poste", master.getPosition(),
                        "email", master.getEmail(),
                        "numéro de téléphone", master.getPhoneNumber()
                ),
                InvalidMasterDataException::new
        );

        ValidationUtils.checkConflicts(
                Map.of(
                        "L'email du maître d'apprentissage existe déjà.", () ->
                                masterRepository.existsByEmailAndIdNot(master.getEmail().trim(), master.getId()),
                        "Le numéro de téléphone du maître d'apprentissage existe déjà.", () ->
                                masterRepository.existsByPhoneNumberAndIdNot(master.getPhoneNumber().trim(), master.getId()),
                        "Le nom et prénom du maître d'apprentissage existent déjà.", () ->
                                masterRepository.existsByLastNameAndFirstNameAndIdNot(
                                        master.getLastName().trim(), master.getFirstName().trim(), master.getId()
                                )
                ),
                MasterAlreadyExistsException::new
        );
    }

    private void applyChanges(Master targetMaster, Master sourceMaster) {
        if (!sourceMaster.getLastName().equals(targetMaster.getLastName())) {
            targetMaster.setLastName(sourceMaster.getLastName());
        }

        if (!sourceMaster.getFirstName().equals(targetMaster.getFirstName())) {
            targetMaster.setFirstName(sourceMaster.getFirstName());
        }

        if (!sourceMaster.getPosition().equals(targetMaster.getPosition())) {
            targetMaster.setPosition(sourceMaster.getPosition());
        }

        if (!sourceMaster.getEmail().equals(targetMaster.getEmail())) {
            targetMaster.setEmail(sourceMaster.getEmail());
        }

        if (!sourceMaster.getPhoneNumber().equals(targetMaster.getPhoneNumber())) {
            targetMaster.setPhoneNumber(sourceMaster.getPhoneNumber());
        }

        if (sourceMaster.getComments() != null &&
                !sourceMaster.getComments().equals(targetMaster.getComments())) {
            targetMaster.setComments(sourceMaster.getComments());
        }
    }
}
