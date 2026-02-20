package mca.nitt.question_service_generator.dto;

import lombok.Data;

@Data
public class QuestionRequest {

    private String text;
    private String category;
    private Integer numQuestions;
}