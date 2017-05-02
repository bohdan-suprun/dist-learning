package ua.nure.dl.repo.graph.impl;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.nure.dl.model.dto.Competency;
import ua.nure.dl.model.dto.Subject;
import ua.nure.dl.model.entity.CompetencyEntity;
import ua.nure.dl.model.entity.SubjectEntity;
import ua.nure.dl.repo.graph.CompetencySubjectGraphDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Bohdan_Suprun
 */
@Repository
public class CompetencySubjectGraphDaoImpl implements CompetencySubjectGraphDao {

    private static final Label COMPETENCY_LABEL = DynamicLabel.label(Competency.class.getTypeName());
    private static final Label SUBJECT_LABEL = DynamicLabel.label(Subject.class.getTypeName());

    private static final RelationshipType RELATIONSHIP_NAME = DynamicRelationshipType
            .withName("DEPENDS_ON");

    @Autowired
    private GraphDatabaseService graphDatabaseService;

    @Override
    public Collection<SubjectEntity> findAllSubjectsForCompetency(long competency) {
        Transaction tr = graphDatabaseService.beginTx();

        try {
            Node node = graphDatabaseService.getNodeById(competency);
            Collection<SubjectEntity> result = new ArrayList<>();

            for (Relationship relationship : node.getRelationships(Direction.OUTGOING, RELATIONSHIP_NAME)) {
                result.add(toSubjectEntity(relationship.getEndNode()));
            }

            tr.success();
            return result;
        } catch (Exception ex) {
            tr.failure();
        } finally {
            tr.close();
        }

        return Collections.emptyList();
    }

    @Override
    public void addRelation(SubjectEntity subject, CompetencyEntity competency) {
        Transaction tr = graphDatabaseService.beginTx();

        try {
            Node subjectNode = graphDatabaseService.getNodeById(subject.getId());
            Node competencyNode = graphDatabaseService.getNodeById(competency.getId());

            Relationship relationship = competencyNode.createRelationshipTo(subjectNode, RELATIONSHIP_NAME);

            if (relationship == null) {
                throw new IllegalArgumentException("Can't create relationship");
            }

            System.out.println("Saved: " + relationship);
            tr.success();
        } catch (Exception ex) {
            tr.failure();
            throw ex;
        } finally {
            tr.close();
        }

    }

    @Override
    public SubjectEntity save(SubjectEntity subjectEntity) {
        Transaction tr = graphDatabaseService.beginTx();

        try {
            Node node = graphDatabaseService.createNode(SUBJECT_LABEL);
            toNode(node, subjectEntity);
            tr.success();
            System.out.println("Saved: " + subjectEntity);
            return subjectEntity;
        } catch (Exception ex) {
            tr.failure();
            throw ex;
        } finally {
            tr.close();
        }
    }

    @Override
    public CompetencyEntity save(CompetencyEntity competencyEntity) {
        Transaction tr = graphDatabaseService.beginTx();

        try {
            Node node = graphDatabaseService.createNode(COMPETENCY_LABEL);
            toNode(node, competencyEntity);
            tr.success();
            System.out.println("Saved: " + competencyEntity);
            return competencyEntity;
        } catch (Exception ex) {
            tr.failure();
            throw ex;
        } finally {
            tr.close();
        }
    }

    private SubjectEntity toSubjectEntity(Node node) {
        return new SubjectEntity(node.getId(), node.getProperty("name").toString());
    }

    private Node toNode(Node node, SubjectEntity subjectEntity) {
        node.setProperty("name", subjectEntity.getName());
        node.setProperty("id", subjectEntity.getId());

        return node;
    }

    private Node toNode(Node node, CompetencyEntity competencyEntity) {
        node.setProperty("name", competencyEntity.getName());
        node.setProperty("id", competencyEntity.getId());

        return node;
    }
}
