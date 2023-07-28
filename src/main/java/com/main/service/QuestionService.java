package com.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.main.dao.QuestionDao;
import com.main.entity.Question;

//for a service layer
@Service
public class QuestionService {
	@Autowired
	QuestionDao questionDao;
	public ResponseEntity<List<Question>> getAllQuestions() 	
	{
		try {
		return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
		return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionByLevel(String level) {
		try {
		return new ResponseEntity<>(questionDao.findByLevel(level),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<String> addQuestion(Question question) {
		try {
		questionDao.save(question);
		return new ResponseEntity<>("added", HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("not added", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<String> deleteQuestion(Integer id){
		try {
		questionDao.deleteById(id);
		return new ResponseEntity<>("deleted", HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
	}

	
	public String updateQuestion(@RequestBody Question question) {
		questionDao.save(question);
		return "saved";
	}
	
}
