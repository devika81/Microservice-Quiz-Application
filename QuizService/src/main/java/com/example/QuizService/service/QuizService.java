package com.example.QuizService.service;


import com.example.QuizService.dao.QuizDao;
import com.example.QuizService.feign.QuizInterface;
import com.example.QuizService.model.QuestionWrapper;
import com.example.QuizService.model.Quiz;
import com.example.QuizService.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List <Integer> question_ids = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(question_ids);
        quizDao.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Quiz quiz = quizDao.findById(id).get();
        List <Integer> question_Ids = quiz.getQuestionIds();
        ResponseEntity <List<QuestionWrapper>> questions = quizInterface.generateQuestionsFromIds(question_Ids);
        return questions;

    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        ResponseEntity <Integer> score = quizInterface.generateScore(responses);
        return  score;
    }
}
