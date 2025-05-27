package ru.bmstu.iu7.API.model;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReqCache {
    int id_questionnaire;
    private List<IQuestionnaire> m_req_cache = new ArrayList<>();

    public ReqCache(int id_questionnaire, List<IQuestionnaire> req_cache) {
        this.id_questionnaire = id_questionnaire;
        this.m_req_cache = req_cache;
    }
    public List<IQuestionnaire>get_req_cache() {
        return m_req_cache;
    }
    public void insert_req_cache(IQuestionnaire req_cache) {
        m_req_cache.add(req_cache);
    }
    public void setM_req_cache(List<IQuestionnaire> req_cache) {
        this.m_req_cache = req_cache;
    }
    public void delete_from_cache(IQuestionnaire questionnaire) {
        m_req_cache.remove(questionnaire);
    }

}
