package ua.nure.dl.competencymanager.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Bohdan_Suprun
 */
@RestControllerAdvice
public class ExceptionHandler {

    private static final String DB_ERROR = "DB error occurred: %s";
    private static final String JSON_ERROR = "{\"errorMessage\":\"%s\"}";

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public String handleException(Exception cause, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        cause.printStackTrace();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        return String.format(JSON_ERROR, translateException(cause));
    }

    private String translateException(Exception cause) {
        if (isConstraintViolationException(cause)) {
            return String.format(DB_ERROR, "uniqness constraint violation");
        }

        if (isDbException(cause)) {
            return String.format(DB_ERROR, "Undefined error");
        }

        return "Undefined error";

    }

    private boolean isConstraintViolationException(Exception ex) {
        return isDbException(ex) && ex.getMessage().contains("constraint");
    }

    private boolean isDbException(Exception ex) {
        return ex instanceof DataIntegrityViolationException;
    }

}
