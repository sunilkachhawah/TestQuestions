package com.main.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.main.entity.Question;

public interface QuestionDao extends JpaRepository<Question, Integer> 
{
	List<Question> findByLevel(String level);
	List<Question> findByCategory(String category);
	void deleteById(Integer id);

	@Query(value="SELECT * FROM Question q Where q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	List<Question> findRandomQuestionByCategory(String category, int numQ);
}
