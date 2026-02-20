package mca.nitt.question_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    private String id;
    private String questionTitle;
    private String questionCategory;
    private ArrayList<String> options;
    private String correctOption;
}