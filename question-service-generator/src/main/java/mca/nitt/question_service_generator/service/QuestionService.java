package mca.nitt.question_service_generator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mca.nitt.question_service_generator.dto.QuestionRequest;
import mca.nitt.question_service_generator.model.Question;
import mca.nitt.question_service_generator.repository.QuestionRepository;
import mca.nitt.question_service_generator.utils.JsonExtractor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
@RequiredArgsConstructor
public class QuestionService {

    private final GeminiService geminiService;
    private final JsonExtractor jsonExtractor;
    private final QuestionRepository questionRepository;

    public List<Question> generateAndStore(QuestionRequest request) throws Exception {

        String prompt = """
                Generate %d MCQs from the text.

                Return ONLY JSON array.
                Each question must have:
                questionTitle, options(4), correctOption.

                Text:
                %s
                """.formatted(request.getNumQuestions(), request.getText());

        String raw = geminiService.generateContent(prompt);

        List<Question> questions = jsonExtractor.parse(raw);

        // set category from request
        questions.forEach(q -> q.setQuestionCategory(request.getCategory()));

        return questionRepository.saveAll(questions);
    }
}