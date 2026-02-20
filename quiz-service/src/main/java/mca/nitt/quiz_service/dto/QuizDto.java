package mca.nitt.quiz_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private String category;
    private int numQuestions;
    private String quizTitle;
}