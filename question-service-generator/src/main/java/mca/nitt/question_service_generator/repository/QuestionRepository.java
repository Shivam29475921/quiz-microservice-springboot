package mca.nitt.question_service_generator.repository;

import mca.nitt.question_service_generator.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
}