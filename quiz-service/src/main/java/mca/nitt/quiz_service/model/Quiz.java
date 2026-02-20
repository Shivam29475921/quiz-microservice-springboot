package mca.nitt.quiz_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "quiz")
public class Quiz {

    @Id
    private String id;
    private String quizCategory;
    private String quizTitle;
    private List<String> questionIds;
}