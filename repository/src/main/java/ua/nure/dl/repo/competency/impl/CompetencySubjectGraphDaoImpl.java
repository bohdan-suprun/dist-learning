package ua.nure.dl.repo.competency.impl;

import org.springframework.stereotype.Service;
import ua.nure.dl.model.Competency;
import ua.nure.dl.model.Subject;
import ua.nure.dl.repo.competency.CompetencySubjectGraphDao;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
@Service
public class CompetencySubjectGraphDaoImpl implements CompetencySubjectGraphDao {

    @Override
    public Collection<Subject> getAllSubjectsForCompetency(long competency) {
        return Arrays.asList(new Subject(1, "test1"), new Subject(2, "subject2"));
    }

    @Override
    public boolean addRelation(Subject subject, Competency competency) {
        return subject.getId() != 0 && competency.getId() != 0;
    }
}
