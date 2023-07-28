package com.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
