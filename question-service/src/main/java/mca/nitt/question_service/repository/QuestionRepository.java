package mca.nitt.question_service.repository;

import mca.nitt.question_service.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    List<Question> findAllByQuestionCategoryContaining(String category);
    List<Question> findAllByQuestionCategory(String category);
}