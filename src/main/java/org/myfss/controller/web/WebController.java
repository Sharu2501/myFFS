package org.myfss.controller.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myfss.model.*;
import org.myfss.service.ApprenticeServiceImpl;
import org.myfss.service.CompanyServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/apprentices")
public class WebController {

    private final ApprenticeServiceImpl apprenticeServiceImpl;
    private final CompanyServiceImpl companyServiceImpl;

    @GetMapping("/")
    public String home() {
        return "redirect:/apprentices";
    }

    @GetMapping
    public String listApprentices(Model model) {
        model.addAttribute("apprentices", apprenticeServiceImpl.getAllApprentices());
        model.addAttribute("alumni", apprenticeServiceImpl.getAllAlumni());
        return "dashboard";
    }

    @GetMapping("/search")
    public String searchApprentices(@RequestParam(required = false) String name, @RequestParam(required = false) String company, @RequestParam(required = false) String missionKeyword, @RequestParam(required = false) String academicYear, Model model) {
        model.addAttribute("apprentices", apprenticeServiceImpl.searchApprentices(name, company, missionKeyword, academicYear));
        model.addAttribute("searchName", name);
        model.addAttribute("searchCompany", company);
        model.addAttribute("searchMission", missionKeyword);
        model.addAttribute("searchYear", academicYear);
        return "dashboard";
    }

    @GetMapping("/{id}")
    public String viewApprentice(@PathVariable Long id, Model model) {
        model.addAttribute("apprentice", apprenticeServiceImpl.getApprenticeById(id));
        return "apprentice-detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("apprentice", apprenticeServiceImpl.getApprenticeById(id));
        model.addAttribute("company", companyServiceImpl.getAllCompanies());
        model.addAttribute("apprenticeId", id);
        return "apprentice-edit";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("apprentice", new Apprentice());
        model.addAttribute("companies", companyServiceImpl.getAllCompanies());
        return "apprentice-form";
    }

    @PostMapping
    public String createApprentice(@Valid @ModelAttribute("apprentice") Apprentice apprentice, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("companies", companyServiceImpl.getAllCompanies());
            return "apprentice-form";
        }

        try {
            Apprentice created = apprenticeServiceImpl.createApprentice(apprentice);

            redirectAttributes.addFlashAttribute("successMessage",
                    "Apprenti créé avec succès !");

            return "redirect:/apprentices/" + created.getId();

        } catch (Exception e) {
            model.addAttribute("companies", companyServiceImpl.getAllCompanies());
            model.addAttribute("errorMessage", e.getMessage());
            return "apprentice-form";
        }
    }

    @PostMapping("/{id}/update")
    public String updateApprentice(@PathVariable Long id, @Valid @ModelAttribute("apprentice") Apprentice apprentice, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("companies", companyServiceImpl.getAllCompanies());
            model.addAttribute("apprenticeId", id);
            return "apprentice-edit";
        }

        try {
            apprenticeServiceImpl.updateApprentice(id, apprentice);

            redirectAttributes.addFlashAttribute("successMessage",
                    "Apprenti modifié avec succès !");
            return "redirect:/apprentices/" + id;

        } catch (Exception e) {
            model.addAttribute("companies", companyServiceImpl.getAllCompanies());
            model.addAttribute("apprenticeId", id);
            model.addAttribute("errorMessage", e.getMessage());
            return "apprentice-edit";
        }
    }

    @PostMapping("/new-year")
    public String createNewYear(RedirectAttributes redirectAttributes) {
        apprenticeServiceImpl.createNewAcademicYear();

        redirectAttributes.addFlashAttribute("successMessage",
                "Nouvelle année académique créée avec succès !");
        return "redirect:/apprentices";
    }

    @PostMapping("/import")
    public String importCSV(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            int count = apprenticeServiceImpl.importApprenticesFromCSV(file);
            redirectAttributes.addFlashAttribute("successMessage", count + " apprentis importés avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de l'import : " + e.getMessage());
        }
        return "redirect:/apprentices";
    }
}
