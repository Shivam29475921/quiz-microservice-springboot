package mca.nitt.question_service_generator.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mca.nitt.question_service_generator.model.Question;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
@RequiredArgsConstructor
public class JsonExtractor {

    private final ObjectMapper objectMapper;

    public List<Question> parse(String rawText) throws Exception {

        if (rawText == null || rawText.isBlank()) {
            throw new RuntimeException("Empty AI response");
        }

        // 🔥 remove ```json and ```
        String cleaned = rawText
                .replace("```json", "")
                .replace("```", "")
                .trim();

        System.out.println("==================================================");

        // 🔥 remove newlines (same as Python)
        cleaned = cleaned.replace("\n", "").trim();

        System.out.println(cleaned);

        // 🔥 direct JSON parse
        Question[] questions = objectMapper.readValue(cleaned, Question[].class);

        return Arrays.asList(questions);
    }
}