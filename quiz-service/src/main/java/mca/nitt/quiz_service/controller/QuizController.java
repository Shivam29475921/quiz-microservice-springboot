package mca.nitt.quiz_service.controller;

import mca.nitt.quiz_service.dto.QuizDto;
import mca.nitt.quiz_service.feign.QuizInterface;
import mca.nitt.quiz_service.model.Question;
import mca.nitt.quiz_service.model.Quiz;
import mca.nitt.quiz_service.model.UserResponse;
import mca.nitt.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @Autowired
    QuizInterface quizInterface;

    @GetMapping("{id}")
    public ResponseEntity<?> getQuizById(@PathVariable String id){
        Quiz response = quizService.find(id).orElse(null);
        assert response != null;
        String title = response.getQuizTitle();
        List<Question> questions = (List<Question>) quizInterface.getQuestionsByIds(response.getQuestionIds()).getBody();
        return new ResponseEntity<>(Map.of("title", title, "questions", questions), HttpStatus.OK);

    }

    @PostMapping("create")
    public ResponseEntity<?> createQuiz(@RequestBody QuizDto quizDto){

        quizService.createQuiz(quizDto.getCategory(), quizDto.getQuizTitle(), quizDto.getNumQuestions());
        return new ResponseEntity<>("Created " + quizDto.getQuizTitle() + " Quiz", HttpStatus.CREATED);
    }

    @PostMapping("{id}/submit")
    public ResponseEntity<?> getResults(@PathVariable String id, @RequestBody List<UserResponse> userResponses){
        return new ResponseEntity<>(quizService.calculateScore(id, userResponses), HttpStatus.OK);
    }
}