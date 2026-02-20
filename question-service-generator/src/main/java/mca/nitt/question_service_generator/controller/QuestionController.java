package mca.nitt.question_service_generator.controller;

import lombok.RequiredArgsConstructor;
import mca.nitt.question_service_generator.dto.QuestionRequest;
import mca.nitt.question_service_generator.model.Question;
import mca.nitt.question_service_generator.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/generate")
    public ResponseEntity<List<Question>> generate(@RequestBody QuestionRequest request)
            throws Exception {

        return ResponseEntity.ok(questionService.generateAndStore(request));
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}