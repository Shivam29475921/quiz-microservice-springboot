package mca.nitt.quiz_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user-response")
public class UserResponse {
    @Id
    private String id;
    private String quizId;
    private String questionId;
    private String userAnswer;

}