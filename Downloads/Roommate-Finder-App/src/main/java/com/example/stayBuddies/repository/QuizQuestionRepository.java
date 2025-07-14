package com.example.stayBuddies.repository;

import com.example.stayBuddies.model.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion,Long> {

}
