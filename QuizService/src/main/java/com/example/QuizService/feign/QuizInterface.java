package com.example.QuizService.feign;

import com.example.QuizService.model.QuestionWrapper;
import com.example.QuizService.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTIONSERVICE")
public interface QuizInterface {

    //generate random question's id's from db based on the category and number of questions we specify
    @GetMapping("/question/generate/questions-ids")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions);


    //generate quiz questions(question ids, questions, options) based on the question ids we provide
    @PostMapping("/question/generateQuestionsFromIds")
    public ResponseEntity <List<QuestionWrapper>> generateQuestionsFromIds(@RequestBody List <Integer> questionIds);


    //generate score for the quiz done by the user
    @PostMapping("/question/generateScore")
    public ResponseEntity <Integer> generateScore(@RequestBody List <Response> responses);



}
