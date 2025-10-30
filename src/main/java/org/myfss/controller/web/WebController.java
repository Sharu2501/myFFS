package org.myfss.controller.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myfss.model.*;
import org.myfss.model.dto.ApprenticeDTO;
import org.myfss.model.mapper.ApprenticeMapper;
import org.myfss.service.ApprenticeService;
import org.myfss.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/apprentices")
public class WebController {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ApprenticeService apprenticeService;
    private final CompanyService companyService;

    @GetMapping("/")
    public String home() {
        return "redirect:/apprentices";
    }

    @GetMapping
    public String listApprentices(Model model) {
        model.addAttribute("apprentices", apprenticeService.getAllApprentices());
        model.addAttribute("alumni", apprenticeService.getAllAlumni());
        return "dashboard";
    }

    @GetMapping("/search")
    public String searchApprentices(@RequestParam(required = false) String name, @RequestParam(required = false) String company, @RequestParam(required = false) String missionKeyword, @RequestParam(required = false) String academicYear, Model model) {
        model.addAttribute("apprentices", apprenticeService.searchApprentices(name, company, missionKeyword, academicYear));
        model.addAttribute("searchName", name);
        model.addAttribute("searchCompany", company);
        model.addAttribute("searchMission", missionKeyword);
        model.addAttribute("searchYear", academicYear);
        return "dashboard";
    }

    @GetMapping("/{id}")
    public String viewApprentice(@PathVariable Long id, Model model) {
        model.addAttribute("apprentice", apprenticeService.getApprenticeById(id));
        return "apprentice-detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("apprentice", apprenticeService.getApprenticeById(id));
        model.addAttribute("company", companyService.getAllCompanies());
        model.addAttribute("apprenticeId", id);
        return "apprentice-edit";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("apprentice", new Apprentice());
        model.addAttribute("companies", companyService.getAllCompanies());
        return "apprentice-form";
    }

    @PostMapping
    public String createApprentice(@Valid @ModelAttribute("apprentice") Apprentice apprentice, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("companies", companyService.getAllCompanies());
            return "apprentice-form";
        }

        try {
            Apprentice created = apprenticeService.createApprentice(apprentice);

            redirectAttributes.addFlashAttribute("successMessage",
                    "Apprenti créé avec succès !");

            return "redirect:/apprentices/" + created.getId();

        } catch (Exception e) {
            model.addAttribute("companies", companyService.getAllCompanies());
            model.addAttribute("errorMessage", e.getMessage());
            return "apprentice-form";
        }
    }

    @PostMapping("/{id}/update")
    public String updateApprentice(@PathVariable Long id, @Valid @ModelAttribute("apprentice") Apprentice apprentice, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("companies", companyService.getAllCompanies());
            model.addAttribute("apprenticeId", id);
            return "apprentice-edit";
        }

        try {
            apprenticeService.updateApprentice(id, apprentice);

            redirectAttributes.addFlashAttribute("successMessage",
                    "Apprenti modifié avec succès !");
            return "redirect:/apprentices/" + id;

        } catch (Exception e) {
            model.addAttribute("companies", companyService.getAllCompanies());
            model.addAttribute("apprenticeId", id);
            model.addAttribute("errorMessage", e.getMessage());
            return "apprentice-edit";
        }
    }

    @PostMapping("/new-year")
    public String createNewYear(RedirectAttributes redirectAttributes) {
        apprenticeService.createNewAcademicYear();

        redirectAttributes.addFlashAttribute("successMessage",
                "Nouvelle année académique créée avec succès !");
        return "redirect:/apprentices";
    }

    @PostMapping("/upload")
    public String addJsonApprentice(@RequestParam("file") MultipartFile file,
                                    RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Fichier vide !");
            return "redirect:/apprentices";
        }

        try {
            ObjectMapper mapper = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            List<ApprenticeDTO> apprentices = mapper.readValue(
                    file.getInputStream(),
                    new TypeReference<List<ApprenticeDTO>>() {}
            );
            apprenticeService.importFromDTOList(apprentices);

            redirectAttributes.addFlashAttribute("successMessage",
                    "Tous les apprentis ont été créés ou mis à jour avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Erreur lors de l'import JSON : " + e.getMessage());
        }

        return "redirect:/apprentices";
    }



}
