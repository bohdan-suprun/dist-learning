package ua.nure.dl.competencymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.nure.dl.competencymanager.dto.CompetencyDto;
import ua.nure.dl.competencymanager.dto.CompetencySubjectRelationDto;
import ua.nure.dl.competencymanager.dto.SubjectDto;
import ua.nure.dl.competencymanager.service.CompetencySubjectService;
import ua.nure.dl.model.Competency;
import ua.nure.dl.model.Subject;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public String handleException(Throwable cause, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        return String.format("Exception %s", cause.getMessage());
    }


}
