package ua.nure.dl.competencymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.dl.competencymanager.service.CompetencySubjectService;
import ua.nure.dl.model.dto.Competency;
import ua.nure.dl.model.dto.Subject;

import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
@RestController
@Controller
@ResponseBody
public class CompetencyController {

    @Autowired
    CompetencySubjectService competencySubjectService;

    @GetMapping("/competency/{id}/subjects")
    public Collection<Subject> getRequiredSubjects(@PathVariable long id) {
        return competencySubjectService.getAllSubjectsForCompetency(id);
    }

    @PostMapping("/competency")
    public Competency createCompetency(@RequestBody Competency competency) {
        return competencySubjectService.addCompetency(competency);
    }

    @PostMapping("/subject")
    public Subject createSubject(@RequestBody Subject subject) {
        return competencySubjectService.addSubject(subject);
    }

    @PostMapping("/competency/{compId}/subject/{subId}")
    public void addRelation(@PathVariable long compId, @PathVariable long subId) {
        competencySubjectService.addRelation(compId, subId);
    }
}
