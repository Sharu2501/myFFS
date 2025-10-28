package org.myfss.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myfss.model.*;
import org.myfss.service.ApprenticeService;
import org.myfss.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/apprentices")
public class WebController {

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
    public String createApprentice(@Valid @ModelAttribute Apprentice apprentice, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("companies", companyService.getAllCompanies());
            return "apprentice-form";
        }

        Apprentice createdApprentice = apprenticeService.createApprentice(apprentice);

        redirectAttributes.addFlashAttribute("successMessage",
                "Apprenti créé avec succès !");
        return "redirect:/apprentices/" + createdApprentice.getId();
    }

    @PostMapping("/{id}/update")
    public String updateApprentice(@PathVariable Long id, @Valid @ModelAttribute Apprentice apprentice, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("company", companyService.getAllCompanies());
            model.addAttribute("apprentice", apprentice);
            model.addAttribute("apprenticeId", id);
            return "apprentice-edit";
        }

        // apprenticeService.updateApprentice(id, apprentice);

        redirectAttributes.addFlashAttribute("successMessage",
                "Apprenti modifié avec succès !");
        return "redirect:/apprentices/" + id;
    }

    @PostMapping("/new-year")
    public String createNewYear(RedirectAttributes redirectAttributes) {
        apprenticeService.createNewAcademicYear();

        redirectAttributes.addFlashAttribute("successMessage",
                "Nouvelle année académique créée avec succès !");
        return "redirect:/apprentices";
    }
}
