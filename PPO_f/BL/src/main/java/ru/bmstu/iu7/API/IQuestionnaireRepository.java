package ru.bmstu.iu7.API;

import ru.bmstu.iu7.API.model.Information;
import ru.bmstu.iu7.API.model.Questionnaire;
import ru.bmstu.iu7.API.model.User;

import java.util.List;

public interface IQuestionnaireRepository {
    Questionnaire createQuestionnaire(Information information, Information search_info) throws Exception;
    Questionnaire findQuestionnaire(int id) throws Exception;
    Questionnaire delete(int id) throws Exception;
    Questionnaire delete(Questionnaire questionnaire) throws Exception;
    Questionnaire update(Questionnaire questionnaire) throws Exception;
    List<Questionnaire> findAll();
}
