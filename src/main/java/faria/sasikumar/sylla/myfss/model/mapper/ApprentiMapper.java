package faria.sasikumar.sylla.myfss.model.mapper;

import faria.sasikumar.sylla.myfss.model.Apprenti;
import faria.sasikumar.sylla.myfss.model.dto.ApprentiDTO;

public class ApprentiMapper {

    public ApprentiDTO map(Apprenti apprenti){
        return ApprentiDTO.builder()
                .id(apprenti.getId())
                .nom(apprenti.getNom())
                .prenom(apprenti.getPrenom())
                .email(apprenti.getEmail())
                .telephone(apprenti.getTelephone())
                .programme(apprenti.getProgramme())
                .majeure(apprenti.getMajeure())
                .annee(apprenti.getAnnee())
                .archived(apprenti.isArchived())
                .build();
    }
}
