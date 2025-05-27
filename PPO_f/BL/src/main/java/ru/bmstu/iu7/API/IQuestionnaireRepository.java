package ru.bmstu.iu7.API;

import ru.bmstu.iu7.API.model.IInformation;
import ru.bmstu.iu7.API.model.IQuestionnaire;
import ru.bmstu.iu7.API.model.IUser;

import java.util.List;

public interface IQuestionnaireRepository {
    IQuestionnaire createQuestionnaire(IUser user, IInformation information,
                                       IInformation search_info) throws Exception;
    IQuestionnaire findQuestionnaire(Long id) throws Exception;
    void delete(Long id) throws Exception;
    void delete(IQuestionnaire questionnaire) throws Exception;
    IQuestionnaire update(IQuestionnaire questionnaire) throws Exception;
    List<IQuestionnaire> find_interval(int lower, int upper);
    List<? extends IQuestionnaire> sort_questionnaire();
    List<IQuestionnaire> find_user_questionnaire(Long id) throws Exception;
    // List<Question> get_questions(Questionnaire questionnaire) throws Exception;
}
