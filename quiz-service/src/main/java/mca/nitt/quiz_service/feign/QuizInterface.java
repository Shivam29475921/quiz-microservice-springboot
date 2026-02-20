package mca.nitt.quiz_service.feign;

import mca.nitt.quiz_service.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    //getAllQuestions
    @PostMapping("questions/getQuestions")
    public ResponseEntity<?> getQuestionsByIds(@RequestBody List<String> questionIds);


    //generate Questions
    @GetMapping("questions/generate")
    public ResponseEntity<?> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numQuestions);


    //calculate Score
    @PostMapping("questions/getScore")
    public ResponseEntity<?> getScore(@RequestBody List<UserResponse> responses);

}