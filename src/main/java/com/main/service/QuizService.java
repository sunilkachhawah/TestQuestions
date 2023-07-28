package com.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.main.dao.QuestionDao;
import com.main.dao.QuizDao;
import com.main.entity.Question;
import com.main.entity.QuestionWrapper;
import com.main.entity.Quiz;
import com.main.entity.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> question=questionDao.findRandomQuestionByCategory(category, numQ);
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(question);
		quizDao.save(quiz);
		return new ResponseEntity<>("quiz cretaed", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		//maybe id quiz is available or not
		Optional<Quiz> quiz=quizDao.findById(id);
		//when we are using optional we have to use get()
		List<Question> questionsFromDB=quiz.get().getQuestions();
		//empty array list for storing questions without answers
		List<QuestionWrapper> questionsForUser=new ArrayList<>();
		for(Question q: questionsFromDB) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionsForUser.add(qw);
		}
		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz=quizDao.findById(id).get();
		List<Question> questions=quiz.getQuestions();
		int right=0;
		int i=0;
		for(Response response:responses) {
			if(response.getResponse().equals(questions.get(i).getAnswer())) {
			right++;
				i++;
			}
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
	
}
