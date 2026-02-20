package mca.nitt.question_service_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class QuestionServiceGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionServiceGeneratorApplication.class, args);
	}

}