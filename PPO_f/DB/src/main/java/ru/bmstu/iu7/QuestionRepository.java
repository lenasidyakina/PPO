package ru.bmstu.iu7;

import ru.bmstu.iu7.API.IQuestionRepository;
import ru.bmstu.iu7.API.model.ITag;
import ru.bmstu.iu7.API.model.IQuestion;


import java.util.List;

public class QuestionRepository implements IQuestionRepository {
    @Override
    public List<IQuestion> get_questions() throws Exception {
        return null;
    }

    @Override
    public List<ITag> get_question_tags(int id) throws Exception {
        return null;
    }
}
