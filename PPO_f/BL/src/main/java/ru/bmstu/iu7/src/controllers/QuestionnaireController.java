package ru.bmstu.iu7.src.controllers;

import ru.bmstu.iu7.API.IML_port;
import ru.bmstu.iu7.API.IQuestionRepository;
import ru.bmstu.iu7.API.IQuestionnaireRepository;
import ru.bmstu.iu7.API.IReqCacheRepository;
import ru.bmstu.iu7.API.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuestionnaireController
{
    private IML_port m_ml;
    private IQuestionnaire m_active_questionnaire;
    private ReqCache m_req_cache;
    private IQuestionnaireRepository m_questionnaireRepository;
    private IReqCacheRepository m_req_cache_repository;
    private IQuestionRepository m_questionRepository;


    public QuestionnaireController(IML_port imlPort, IQuestionnaireRepository questionnaireRepo, IReqCacheRepository reqCacheRepo, IQuestionRepository questionRepository)
    {
        this.m_questionnaireRepository = questionnaireRepo;
        this.m_ml = imlPort;
        this.m_req_cache_repository = reqCacheRepo;
        this.m_questionRepository = questionRepository;
        //this.m_req_cache = new ReqCache(this.m_active_questionnaire.get_id(), List.of());
    }

    public List<IQuestionnaire> get_user_questionnaies(Long id) throws Exception {
        return m_questionnaireRepository.find_user_questionnaire(id);
    }

    public void set_active_questionnaire(IQuestionnaire questionnaire) {

        this.m_active_questionnaire = questionnaire;
    }
    public void set_req_cache(ReqCache req_cache) {
        this.m_req_cache = req_cache;
    }

    public IQuestionnaire get_active_questionnaire() {
        return this.m_active_questionnaire;
    }


    @SuppressWarnings("unchecked")
    public IQuestionnaire create_questionnaire(IUser user, IInformation information,
                                              IInformation search_information) throws Exception {
        List<IExtendedAnswer> extended_answers = (List<IExtendedAnswer>)information.getExtendedAnswers();
        for (IExtendedAnswer extendedAnswer : extended_answers){
            extendedAnswer.setTags(m_ml.get_tags_names((extendedAnswer.getQuestion()).getQuestion(), extendedAnswer.getAnswer(),
                    (List<ITag>)extendedAnswer.getTags()));

        }
        information.setExtendedAnswers(extended_answers);

        List<IExtendedAnswer> extended_answers_s = (List<IExtendedAnswer>)search_information.getExtendedAnswers();
        for (IExtendedAnswer extendedAnswer : extended_answers_s){
            extendedAnswer.setTags(m_ml.get_tags_names((extendedAnswer.getQuestion()).getQuestion(),
                    extendedAnswer.getAnswer(), (List<ITag>)extendedAnswer.getTags()));

        }
        search_information.setExtendedAnswers(extended_answers_s);
        return m_questionnaireRepository.createQuestionnaire(user, information, search_information);
    }

    public void add_fav(IQuestionnaire questionnaire) throws Exception {
        m_active_questionnaire.addFav(questionnaire);
        m_req_cache_repository.delete(questionnaire.getId());
        m_req_cache.delete_from_cache(questionnaire);
        m_questionnaireRepository.update(m_active_questionnaire);
    }

    public void add_black(IQuestionnaire questionnaire) throws Exception {
        m_active_questionnaire.addBlack(questionnaire);
        m_req_cache_repository.delete(questionnaire.getId());
        m_req_cache.delete_from_cache(questionnaire);
        m_questionnaireRepository.update(m_active_questionnaire);
    }

    public boolean is_in_black(IQuestionnaire questionnaire) {
        for (IQuestionnaire quest : questionnaire.getBlackList()){
            if (quest.getId() == questionnaire.getId()){
                return true;
            }
        }
        return false;
    }

    public boolean is_in_fav(IQuestionnaire questionnaire) {
        for (IQuestionnaire quest : questionnaire.getFavList()){
            if (quest.getId() == questionnaire.getId()){
                return true;
            }
        }
        return false;
    }

    public void delete(IQuestionnaire questionnaire) throws Exception {
        m_questionnaireRepository.delete(questionnaire);
    }

    public void censor(IQuestionnaire questionnaire) throws Exception {
        m_active_questionnaire.setCensored(true);
        m_questionnaireRepository.update(questionnaire);
    }

    public List<IQuestionnaire> get_questionnairies(int lower, int upper) throws Exception {
        return m_questionnaireRepository.find_interval(lower, upper);
    }

    public void add_in_cache_list(double harmonic_average_norm, Long id_quest) throws Exception {
        m_req_cache_repository.insert(id_quest, harmonic_average_norm);
        m_req_cache.insert_req_cache(m_questionnaireRepository.findQuestionnaire(id_quest));
    }

    public void update_req_cache() throws Exception {
        List<Map.Entry<Long, Double>> req_cache =
                new ArrayList<>(m_req_cache_repository.findAll(m_active_questionnaire.getId()));
        List<IQuestionnaire> req_questionnaires = new ArrayList<>();
        Collections.sort(req_cache, Map.Entry.comparingByValue());


        int limit = Math.min(500, req_cache.size());

        List<Long> rec_quest_id = req_cache.stream()
                .limit(500)
                .map(Map.Entry::getKey)
                .toList();

        for (long id : rec_quest_id){
            req_questionnaires.add(m_questionnaireRepository.findQuestionnaire(id));
        }
        m_req_cache.setM_req_cache(req_questionnaires.subList(0,limit));
    }

    public List<IQuestionnaire> get_quest_in_cache() throws Exception {
        return m_req_cache.get_req_cache();//.subList(0, 0);
    }

    public void sort_all_questionnaire(){m_questionnaireRepository.sort_questionnaire();}
}
