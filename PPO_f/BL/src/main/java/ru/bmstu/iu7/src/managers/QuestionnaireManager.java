package ru.bmstu.iu7.src.managers;

import ru.bmstu.iu7.API.model.IInformation;
import ru.bmstu.iu7.API.model.IQuestionnaire;
import ru.bmstu.iu7.API.model.IUser;
import ru.bmstu.iu7.src.controllers.QuestionnaireController;

import java.util.List;

public class QuestionnaireManager {
    private QuestionnaireController m_questionnaireController;
    public QuestionnaireManager(QuestionnaireController controller) {
        m_questionnaireController = controller;
    }
    public IQuestionnaire get_active_questionnaire() {
        return m_questionnaireController.get_active_questionnaire();
    }
    public IQuestionnaire create(IUser user, IInformation information,
                                 IInformation search_information) throws Exception {
        return m_questionnaireController.create_questionnaire(user, information, search_information);
    }
    public void add_fav(IQuestionnaire questionnaire) throws Exception {
        m_questionnaireController.add_fav(questionnaire);
    }

    public void add_black(IQuestionnaire questionnaire) throws Exception {
        m_questionnaireController.add_black(questionnaire);
    }

    public void delete(IQuestionnaire questionnaire) throws Exception {
        m_questionnaireController.delete(questionnaire);
    }

    public void censor(IQuestionnaire questionnaire) throws Exception {
        m_questionnaireController.censor(questionnaire);
    }
}
