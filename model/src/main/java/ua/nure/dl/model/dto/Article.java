package ua.nure.dl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bohdan_Suprun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    private long id;
    private String title;

    private String subjectName;
    private long subjectId;

    private String text;
    private boolean hidden;

    private String authorName;
    private long authorId;
}
