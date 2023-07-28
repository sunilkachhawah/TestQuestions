package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.main.entity.Question;
import com.main.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestion(){
		return questionService.getAllQuestions();
	}
	
	
	@GetMapping("{category}")
	public ResponseEntity<List<Question>> getQuestionsByCtegory(@PathVariable String category){	
		return questionService.getQuestionByCategory(category);
	}
	
	@GetMapping("level/{level}")
	public ResponseEntity<List<Question>> getQuestionsByLevel(@PathVariable String level){
		return (ResponseEntity<List<Question>>) questionService.getQuestionByLevel(level);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
		return questionService.deleteQuestion(id);
	}
	
	
	@PutMapping("/upadte/{id}")
	public String updateQuestion(@RequestBody Question question) {
		return questionService.updateQuestion(question);
	}
	
	
}
 
