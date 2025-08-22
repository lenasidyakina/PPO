package ru.bmstu.iu7;

import org.springframework.data.domain.Sort;
import ru.bmstu.iu7.API.IQuestionnaireRepository;
import ru.bmstu.iu7.API.model.*;
import ru.bmstu.iu7.impl.*;
import ru.bmstu.iu7.impl.model.ExtendedAnswer;
import ru.bmstu.iu7.impl.model.Information;
import ru.bmstu.iu7.impl.model.Questionnaire;
import ru.bmstu.iu7.impl.model.VariantAnswer;

import java.util.List;
import java.util.Optional;

public class QuestionnaireRepository implements IQuestionnaireRepository {

    DBAPI api;

    public QuestionnaireRepository(DBAPI api) {
        this.api = api;
    }

    @Override
    public IQuestionnaire createQuestionnaire(IUser user,
                                             IInformation information,
                                             IInformation search_info) throws Exception {

        // save information
        IQuestionnaire questionnaire = ModelFactory.makeQuestionnaire(user, information, search_info);
        api.m_questionnaireRepository.save((Questionnaire) questionnaire);
        return questionnaire;
/*
        api.m_userRepository.findById(user.getId()).ifPresent(u -> {
            for (IExtendedAnswer ans : information.getExtendedAnswers()) {
                api.m_extendedAnswerRepository.findById(ans.getId()).ifPresentOrElse(v -> {
                    // todo:
                    v.setTags(ans.getTags());
                    v.setAnswer(ans.getAnswer());
                    v.setQuestion(ans.getQuestion());
                    v.setWeight(ans.getWeight());
                    api.m_extendedAnswerRepository.save(v);
                }, () -> {
                    api.m_extendedAnswerRepository.save((ExtendedAnswer) ans);
                    api.m_questionRepository.findById(ans.getQuestion().getId()).ifPresent(
                            n -> {
                                ans.setQuestion(n);
                            });
                });
            }
            for (IVariantAnswer ans : information.getVariantAnswers()) {
                api.m_variantAnswerRepository.findById(ans.getId()).ifPresentOrElse(v -> {
                    // todo:
                    v.setTag(ans.getTag());
                    v.setQuestion(ans.getQuestion());
                    v.setWeight(ans.getWeight());
                    api.m_variantAnswerRepository.save(v);
                }, () -> {
                    api.m_questionRepository.findById(ans.getQuestion().getId()).ifPresent(
                            n -> {
                                ans.setQuestion(n);
                            });
                    api.m_variantAnswerRepository.save((VariantAnswer) ans);
                });
            }
            questionnaire.setInformation(api.m_informationRepository.save((Information)information));

            // save search_info

            for (IExtendedAnswer ans : search_info.getExtendedAnswers()) {
                api.m_extendedAnswerRepository.findById(ans.getId()).ifPresentOrElse(v -> {
                    // todo:
                    v.setTags(ans.getTags());
                    v.setAnswer(ans.getAnswer());
                    v.setWeight(ans.getWeight());
                    v.setQuestion(ans.getQuestion());
                    api.m_extendedAnswerRepository.save(v);
                }, () -> {
                    Long questionId = ans.getQuestion().getId();

                    api.m_questionRepository.findById(ans.getQuestion().getId()).ifPresent(
                            n -> {
                                ans.setQuestion(n);
                            });
                    api.m_extendedAnswerRepository.save((ExtendedAnswer) ans);
                });
            }
            for (IVariantAnswer ans : search_info.getVariantAnswers()) {
                api.m_variantAnswerRepository.findById(ans.getId()).ifPresentOrElse(v -> {
                    // todo:
                    v.setTag(ans.getTag());
                    v.setWeight(ans.getWeight());
                    v.setQuestion(ans.getQuestion());
                    api.m_variantAnswerRepository.save(v);
                }, () -> {
                    api.m_questionRepository.findById(ans.getQuestion().getId()).ifPresent(
                            n -> {
                                ans.setQuestion(n);
                            });
                    api.m_variantAnswerRepository.save((VariantAnswer) ans);
                });
            }
            questionnaire.setSearchInformation(api.m_informationRepository.save((Information) search_info));
            questionnaire.setUser(u);
            api.m_questionnaireRepository.save((Questionnaire) questionnaire);
        });
        //api.m_questionnaireRepository.save(questionnaire);
        return questionnaire;
        */
    }

    @Override
    public IQuestionnaire findQuestionnaire(Long id) throws Exception {
        return api.m_questionnaireRepository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public void delete(Long id) throws Exception {
        api.m_questionnaireRepository.deleteById(id);
    }

    @Override
    public void delete(IQuestionnaire questionnaire) throws Exception {
        api.m_questionnaireRepository.delete((Questionnaire) questionnaire);
    }

    @Override
    public IQuestionnaire update(IQuestionnaire questionnaire) throws Exception {
        return api.m_questionnaireRepository.save((Questionnaire) questionnaire);
    }

    @Override
    public List<IQuestionnaire> find_interval(int lower, int upper) {
        return List.of();
    }

    @Override
    public List<? extends IQuestionnaire> sort_questionnaire() {
        return api.m_questionnaireRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public List<IQuestionnaire> find_user_questionnaire(Long id) throws Exception {
        return List.of();
    }
}
