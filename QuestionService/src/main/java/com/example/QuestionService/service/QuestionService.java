package com.example.QuestionService.service;

import com.example.QuestionService.dao.QuestionDao;
import com.example.QuestionService.model.Question;
import com.example.QuestionService.model.QuestionWrapper;
import com.example.QuestionService.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity <List<Question>> getAllQuestions() {

        try
        {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity <List<Question>> getQuestionsByCategory(String category) {

        try
        {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity <String> addQuestion(Question question) {

        try
        {
            questionDao.save(question);
            return new ResponseEntity<>("successfully added!", HttpStatus.CREATED);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
            return new ResponseEntity<>("failed to add question", HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {

        List <Integer> questions_ids = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
        return new ResponseEntity<>(questions_ids, HttpStatus.OK);

    }


    public ResponseEntity<List<QuestionWrapper>> generateQuestionsFromIds(List<Integer> questionIds) {

        List <QuestionWrapper> wrappers = new ArrayList<>();
        List <Question> questions = new ArrayList<>();

        for(Integer id : questionIds)
        {
            questions.add(questionDao.findById(id).get());
        }

        for(Question question : questions)
        {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestion_title(question.getQuestion_title());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);

    }

    public ResponseEntity<Integer> generateScore(List<Response> responses) {

        int right_answer = 0;

        for (Response response : responses)
        {
            Question questions = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(questions.getRightAnswer()))
            {
                right_answer++;
            }
        }

        return new ResponseEntity<>(right_answer, HttpStatus.OK);

    }
}
