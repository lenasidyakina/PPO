package ru.bmstu.iu7;

import ru.bmstu.iu7.API.IQuestionnaireRepository;
import ru.bmstu.iu7.API.model.Information;
import ru.bmstu.iu7.API.model.Questionnaire;

import java.util.List;

public class QuestionnaireRepository implements IQuestionnaireRepository {

    @Override
    public Questionnaire createQuestionnaire(Information information, Information search_info) throws Exception {
        return null;
    }

    @Override
    public Questionnaire findQuestionnaire(int id) throws Exception {
        return null;
    }

    @Override
    public Questionnaire delete(int id) throws Exception {
        return null;
    }

    @Override
    public Questionnaire delete(Questionnaire questionnaire) throws Exception {
        return null;
    }

    @Override
    public Questionnaire update(Questionnaire questionnaire) throws Exception {
        return null;
    }

    @Override
    public List<Questionnaire> findAll() {
        return List.of();
    }
}
