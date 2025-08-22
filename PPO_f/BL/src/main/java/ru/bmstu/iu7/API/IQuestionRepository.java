package ru.bmstu.iu7.API;

import ru.bmstu.iu7.API.model.IQuestion;
import ru.bmstu.iu7.API.model.ITag;

import java.util.List;

public interface IQuestionRepository {
    List<IQuestion> get_questions() throws Exception;
    List<ITag> get_question_tags(int id) throws Exception;
}
