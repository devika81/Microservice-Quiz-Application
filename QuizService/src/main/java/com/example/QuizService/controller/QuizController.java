package com.example.QuizService.controller;

import com.example.QuizService.model.QuestionWrapper;
import com.example.QuizService.model.QuizDto;
import com.example.QuizService.model.Response;
import com.example.QuizService.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity <String> createQuiz(@RequestBody QuizDto quizDto)
            //(@RequestParam String category, @RequestParam int numQ, @RequestParam String title)
    {
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
    }


    @PostMapping("/get/{id}")
    public  ResponseEntity <List <QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
    {
        return quizService.getQuizQuestions(id);
    }


    @PostMapping("/submit/{id}")
    public ResponseEntity <Integer> submitQuiz(@PathVariable Integer id, @RequestBody List <Response> responses)
    {
        return quizService.calculateResult(id, responses);
    }
}
