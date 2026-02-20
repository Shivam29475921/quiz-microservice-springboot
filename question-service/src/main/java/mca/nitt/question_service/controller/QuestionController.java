package mca.nitt.question_service.controller;

import mca.nitt.question_service.model.Question;
import mca.nitt.question_service.model.UserResponse;
import mca.nitt.question_service.service.QuestionService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable String id){
        Question response = questionService.findById(id).orElse(null);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getQuestionByCategory(@PathVariable String category){
        List<Question> response = questionService.findByQuestionCategory(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getQuestions(){
        List<Question> response = questionService.findAll();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> postQuestions(@RequestBody List<Question> questions){
        System.out.println(questions);
        questionService.saveAll(questions);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable String id){
        Question question = questionService.findById(id).orElse(null);
        if(question != null)
            questionService.remove(question);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping()
    public ResponseEntity<?> deleteAllQuestions(){
        questionService.removeAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //getAllQuestions
    @PostMapping("getQuestions")
    public ResponseEntity<?> getQuestionsByIds(@RequestBody List<String> questionIds){
        return new ResponseEntity<>(questionService.getQuestionsById(questionIds), HttpStatus.OK);
    }

    //generate Questions
    @GetMapping("generate")
    public ResponseEntity<?> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numQuestions){
        return new ResponseEntity<>(questionService.getQuestionForQuiz(category, numQuestions), HttpStatus.CREATED);
    }

    //calculate Score
    @PostMapping("getScore")
    public ResponseEntity<?> getScore(@RequestBody List<UserResponse> responses){
        return new ResponseEntity<>(questionService.getScore(responses), HttpStatus.OK);
    }

}