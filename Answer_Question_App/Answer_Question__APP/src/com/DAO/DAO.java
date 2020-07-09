package com.DAO;

import com.model.Question;
import java.util.List;

public interface DAO {

    public List<Question> getAllQuestions();

    public List<String> getChoicesBy_QuestionId(int question_id);
}
