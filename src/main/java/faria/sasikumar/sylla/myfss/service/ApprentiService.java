package faria.sasikumar.sylla.myfss.service;

import faria.sasikumar.sylla.myfss.model.Apprenti;
import faria.sasikumar.sylla.myfss.repository.ApprentiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ApprentiService {

    private final ApprentiRepository apprentiRepository;

    public ApprentiService(ApprentiRepository apprentiRepository) {
        this.apprentiRepository = apprentiRepository;
    }

    public List<Apprenti> getAllApprentis() {
        return apprentiRepository.findAll();
    }

    public Apprenti getApprenti(Long id) {
        return apprentiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apprenti non trouvé"));
    }

    @Transactional
    public Apprenti createOrUpdateApprenti(Apprenti apprenti) {
        return apprentiRepository.save(apprenti);
    }

    public void deleteApprenti(Long id) {
        apprentiRepository.deleteById(id);
    }

    public List<Apprenti> searchByNom(String nom) {
        return apprentiRepository.findByNomContainingIgnoreCase(nom);
    }
}