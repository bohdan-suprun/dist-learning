package ua.nure.dl.competencymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.dl.competencymanager.service.CompetencySubjectService;
import ua.nure.dl.model.dto.Competency;
import ua.nure.dl.model.dto.Subject;
import ua.nure.dl.model.dto.SubjectMaterial;
import ua.nure.dl.model.util.EntityToDtoConverter;

import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
@RestController
@ResponseBody
public class CompetencyController {

    @Autowired
    private CompetencySubjectService competencySubjectService;
    @Autowired
    private EntityToDtoConverter converter;

    @GetMapping("/competencies")
    public Iterable<Competency> getAllCompetencies() {
        return converter.convertMultiple(
                competencySubjectService.getAllCompetencies(),
                converter::toCompetency
        );
    }

    @GetMapping("/subjects")
    public Iterable<Subject> getAllSubjects() {
        return converter.convertMultiple(
                competencySubjectService.getAllSubjects(),
                converter::toSubject);
    }

    @GetMapping("/competency/{id}/subjects")
    public Collection<Subject> getRequiredSubjects(@PathVariable long id) {
        return converter.convertMultiple(
                competencySubjectService.getAllSubjectsForCompetency(id),
                converter::toSubject);
    }

    @PostMapping("/competency")
    public Competency createCompetency(@RequestBody Competency competency) {
        return converter.toCompetency(competencySubjectService.addCompetency(competency));
    }

    @PostMapping("/subject")
    public Subject createSubject(@RequestBody Subject subject) {
        return converter.toSubject(competencySubjectService.addSubject(subject));
    }

    @PostMapping("/competency/{compId}/subject/{subId}")
    public Subject addSubjectToCompetency(@PathVariable long compId, @PathVariable long subId) {
        return converter.toSubject(competencySubjectService.addSubjectToCompetency(compId, subId));
    }

    @PostMapping("/subject/{id}/material")
    public void addMaterial(@PathVariable long subjId, @RequestBody SubjectMaterial dto) {
        competencySubjectService.addSubjectMaterial(subjId, dto);
    }

    @GetMapping("/subject/{id}/materials")
    public Collection<SubjectMaterial> getMaterials(@PathVariable long subjId) {
        return converter.convertMultiple(
                competencySubjectService.getSubjectMaterial(subjId),
                converter::toSubjectMaterial);
    }
}
