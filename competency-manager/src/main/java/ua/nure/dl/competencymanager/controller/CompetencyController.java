package ua.nure.dl.competencymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.dl.competencymanager.dto.CompetencyDto;
import ua.nure.dl.competencymanager.dto.CompetencySubjectRelationDto;
import ua.nure.dl.competencymanager.dto.SubjectDto;
import ua.nure.dl.competencymanager.service.CompetencySubjectService;
import ua.nure.dl.model.Competency;
import ua.nure.dl.model.Subject;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
@RestController
@Controller
@RequestMapping("/competency")
@ResponseBody
public class CompetencyController {

    @Autowired
    CompetencySubjectService competencySubjectService;

    @GetMapping("/{id}/subject")
    public Collection<Subject> getRequiredSubjects(long id) {
        return competencySubjectService.getAllSubjectsForCompetency(id);
    }

    @PostMapping
    public Competency createCompetency(@RequestBody CompetencyDto competencyDto) {
        Competency competency = new Competency(competencyDto.getId(), competencyDto.getName());

        return competencySubjectService.addCompetency(competency);
    }

    @PostMapping("/relation")
    public void addRelation(HttpServletResponse response, @RequestBody CompetencySubjectRelationDto relationDto) {
        SubjectDto subjectDto = relationDto.getSubject();
        CompetencyDto competencyDto = relationDto.getCompetency();

        Subject subject = new Subject(subjectDto.getId(), subjectDto.getName());
        Competency competency = new Competency(competencyDto.getId(), competencyDto.getName());

        if (competencySubjectService.addRelation(subject, competency)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
