package com.main.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.main.service.QuizService;
import com.main.entity.QuestionWrapper;
import com.main.entity.Response;

@RestController
@RequestMapping("quiz")
public class QuizController {
	@Autowired
	QuizService quizService;
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
		return quizService.createQuiz(category, numQ, title);
	}
	
	@GetMapping("get/{id}")
	//question wrapper for get quiz question without answer column
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
	{
		return quizService.getQuizQuestions(id);
	}

	@PostMapping("submit/{id}")
	//this will return score in Integer 
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response>responses){
		return quizService.calculateResult(id,responses);
	}
	
}
