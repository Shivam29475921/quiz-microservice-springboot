package mca.nitt.question_service.service;

import mca.nitt.question_service.model.Question;
import mca.nitt.question_service.model.UserResponse;
import mca.nitt.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    public QuestionRepository questionRepository;

    public List<Question> findAll(){
        return questionRepository.findAll();
    }

    public void saveAll(List<Question> questions) {
        questionRepository.saveAll(questions);
    }

    public void removeAll() {
        questionRepository.deleteAll();
    }

    public Optional<Question> findById(String id) {
        return questionRepository.findById(id);
    }

    public void remove(Question question) {
        questionRepository.delete(question);
    }

    public List<Question> findByQuestionCategory(String category) {
        return questionRepository.findAllByQuestionCategoryContaining(category);
    }

    public List<String> getQuestionForQuiz(String category, int numQuestions) {
        List<Question> response = questionRepository.findAllByQuestionCategory(category);

        List<Question> shuffled = new ArrayList<>(response);
        Collections.shuffle(shuffled);

        int count = Math.min(numQuestions, shuffled.size());
        List<Question> questions = shuffled.subList(0, count);
        List<String> questionIds = new ArrayList<>();
        for (Question question : questions)
            questionIds.add(question.getId());
        return questionIds;
    }

    public List<Question> getQuestionsById(List<String> questionIds) {
        List<Question> questions = new ArrayList<>();
        for(String id: questionIds)
            questions.add(questionRepository.findById(id).get());
        return questions;
    }

    public int getScore(List<UserResponse> responses) {
        int score = 0;
        for (UserResponse response: responses){
            String userAnswer = response.getUserAnswer();
            String correctAnswer = questionRepository.findById(response.getQuestionId()).get().getCorrectOption();
            if(userAnswer.equals(correctAnswer)) score++;
        }
        return score;
    }
}