package ru.bmstu.iu7.src.controllers;

import ru.bmstu.iu7.API.IML_port;
import ru.bmstu.iu7.API.IQuestionnaireRepository;
import ru.bmstu.iu7.API.model.ExtendedAnswer;
import ru.bmstu.iu7.API.model.Information;
import ru.bmstu.iu7.API.model.Questionnaire;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireController
{
    private final IML_port m_ml;
    private Questionnaire m_active_questionnaire;
    private IQuestionnaireRepository m_questionnaireRepository;

    public QuestionnaireController(IML_port imlPort, IQuestionnaireRepository questionnaireRepo)
    {
        this.m_questionnaireRepository = questionnaireRepo;
        this.m_ml = imlPort;
    }

    public List<Questionnaire> get_user_questionnaies(int id) throws Exception {
        List<Questionnaire> questionnaireList = get_questionnairies();
        List<Questionnaire> result = new ArrayList<>();
        for (Questionnaire questionnaire : questionnaireList)
            if (questionnaire.user_id == id)
                result.add(questionnaire);
        return result;
    }

    public void set_active_questionnaire(Questionnaire questionnaire) {
        this.m_active_questionnaire = questionnaire;
    }

    public Questionnaire get_active_questionnaire() {
        return this.m_active_questionnaire;
    }


    public Questionnaire create_questionnaire(Information information, Information search_information) throws Exception {
        List<ExtendedAnswer> extended_answers = information.getExtended_answers();
        for (ExtendedAnswer extendedAnswer : extended_answers){
            extendedAnswer.setTags(m_ml.get_tags_names((extendedAnswer.getQuestion()).getQuestion(), extendedAnswer.getAnswer(), extendedAnswer.getTags()));

        }
        information.setExtended_answers(extended_answers);
        return m_questionnaireRepository.createQuestionnaire(information, search_information);
    }

    public void add_fav(Questionnaire questionnaire) throws Exception {
        m_active_questionnaire.add_fav(questionnaire);
    }

    public void add_black(Questionnaire questionnaire) throws Exception {
        m_active_questionnaire.add_black(questionnaire);
    }

    public void delete(Questionnaire questionnaire) throws Exception {
        m_questionnaireRepository.delete(questionnaire);
    }

    public void censor(Questionnaire questionnaire) throws Exception {
        m_active_questionnaire.set_censored(true);
        m_questionnaireRepository.update(questionnaire);
    }

    public List<Questionnaire> get_questionnairies() throws Exception {
        return m_questionnaireRepository.findAll();
    }
}
