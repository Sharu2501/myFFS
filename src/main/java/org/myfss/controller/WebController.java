package org.myfss.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myfss.dto.ApprenticeUpdateDTO;
import org.myfss.model.Apprentice;
import org.myfss.model.Company;
import org.myfss.model.Mission;
import org.myfss.service.ApprenticeService;
import org.myfss.service.CompanyService;
import org.myfss.service.MasterService;
import org.myfss.service.MissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/apprentices")
public class WebController {

    private final ApprenticeService apprenticeService;
    private final MasterService masterService;
    private final MissionService missionService;
    private final CompanyService companyService;

    @GetMapping
    public String listApprentices(Model model) {
        List<Apprentice> apprentices = apprenticeService.getAllApprentices();
        List<Apprentice> alumni = apprenticeService.getAllAlumni();
        model.addAttribute("apprentices", apprentices);
        model.addAttribute("alumni", alumni);
        return "dashboard";
    }

    @GetMapping("/search")
    public String searchApprentices(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String missionKeyword,
            @RequestParam(required = false) String academicYear,
            Model model) {

        List<Apprentice> apprentices = apprenticeService.searchApprentices(
                name, company, missionKeyword, academicYear
        );

        model.addAttribute("apprentices", apprentices);
        model.addAttribute("searchName", name);
        model.addAttribute("searchCompany", company);
        model.addAttribute("searchMission", missionKeyword);
        model.addAttribute("searchYear", academicYear);

        return "dashboard";
    }

    @GetMapping("/{id}")
    public String viewApprentice(@PathVariable Long id, Model model) {
        Apprentice apprentice = apprenticeService.getApprenticeById(id);
        model.addAttribute("apprentice", apprentice);
        return "apprentice-detail";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("apprentice", new Apprentice());
        model.addAttribute("masters", masterService.getAllMasters());
        model.addAttribute("companies", companyService.getAllCompanies());
        return "apprentice-form";
    }

    @PostMapping
    public String createApprentice(
            @Valid @ModelAttribute Apprentice apprentice,
            BindingResult result,
            RedirectAttributes redirectAttributes, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("masters", masterService.getAllMasters());
            model.addAttribute("companies", companyService.getAllCompanies());
            return "apprentice-form";
        }

        Company company = apprentice.getCompany();

        if (company != null) {
            if (company.getId() != null) {
                Company existing = companyService.getCompanyById(company.getId());
                apprentice.setCompany(existing);
            } else if (company.getSocialReason() != null) {
                Company existing = companyService.findBySocialReason(company.getSocialReason());
                if (existing != null) {
                    apprentice.setCompany(existing);
                } else {
                    companyService.saveCompany(company);
                }
            }
        }

        Mission mission = apprentice.getMission();
        missionService.saveMission(mission);

        Apprentice created = apprenticeService.createApprentice(apprentice);
        redirectAttributes.addFlashAttribute("successMessage",
                "Apprenti créé avec succès !");
        return "redirect:/apprentices/" + created.getId();
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Apprentice apprentice = apprenticeService.getApprenticeById(id);

        ApprenticeUpdateDTO dto = ApprenticeUpdateDTO.builder()
                .program(apprentice.getProgram())
                .academicYear(apprentice.getAcademicYear())
                .major(apprentice.getMajor())
                .firstName(apprentice.getFirstName())
                .lastName(apprentice.getLastName())
                .email(apprentice.getEmail())
                .phoneNumber(apprentice.getPhoneNumber())
                .companyId(apprentice.getCompany() != null ? apprentice.getCompany().getId() : null)
                .masterId(apprentice.getMaster() != null ? apprentice.getMaster().getId() : null)
                .comments(apprentice.getComments())
                .tutorFeedback(apprentice.getTutorFeedback())
                .build();

        model.addAttribute("apprenticeUpdateDto", dto);
        model.addAttribute("master", masterService.getAllMasters());
        model.addAttribute("apprentice", apprenticeService.getAllApprentices());
        model.addAttribute("company", companyService.getAllCompanies());
        model.addAttribute("apprenticeId", id);
        return "apprentice-edit";
    }

    @PostMapping("/{id}/update")
    public String updateApprentice(
            @PathVariable Long id,
            @Valid @ModelAttribute("apprenticeUpdateDto") ApprenticeUpdateDTO dto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("master", masterService.getAllMasters());
            model.addAttribute("company", companyService.getAllCompanies());
            model.addAttribute("apprentice", apprenticeService.getAllApprentices());
            model.addAttribute("apprenticeUpdateDto", dto);
            model.addAttribute("apprenticeId", id);
            return "apprentice-edit";
        }

        apprenticeService.updateApprentice(id, dto);
        redirectAttributes.addFlashAttribute("successMessage", "Apprenti modifié avec succès !");
        return "redirect:/apprentices/" + id;
    }

    @PostMapping("/new-year")
    public String createNewYear(RedirectAttributes redirectAttributes) {
        apprenticeService.createNewAcademicYear();
        redirectAttributes.addFlashAttribute("successMessage",
                "Nouvelle année académique créée avec succès !");
        return "redirect:/apprentices";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/apprentices";
    }
}
