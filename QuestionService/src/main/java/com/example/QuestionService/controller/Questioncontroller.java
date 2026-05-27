package com.example.QuestionService.controller;

import com.example.QuestionService.model.Question;
import com.example.QuestionService.model.QuestionWrapper;
import com.example.QuestionService.model.Response;
import com.example.QuestionService.service.QuestionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class Questioncontroller
{

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("/allQuestions")
    public ResponseEntity <List<Question>> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity <List <Question>> getQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity <String> addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }

    //generate random question's id's from db based on the category and number of questions we specify
    @GetMapping("/generate/questions-ids")
    public ResponseEntity <List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions)
    {
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }

    //generate quiz questions(question ids, questions, options) based on the question ids we provide
    @PostMapping("/generateQuestionsFromIds")
    public ResponseEntity <List<QuestionWrapper>> generateQuestionsFromIds(@RequestBody List <Integer> questionIds)
    {
        return questionService.generateQuestionsFromIds(questionIds);
    }

    //generate score for the quiz done by the user
    @PostMapping("/generateScore")
    public ResponseEntity <Integer> generateScore(@RequestBody List <Response> responses)
    {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.generateScore(responses);
    }

}
