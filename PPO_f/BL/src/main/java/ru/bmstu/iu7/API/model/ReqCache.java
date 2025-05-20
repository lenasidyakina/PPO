package ru.bmstu.iu7.API.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReqCache {
    int id_questionnaire;
    private List<Map.Entry<Integer, Double>> m_req_cache = new ArrayList<>();

    public ReqCache(int id_questionnaire, List<Map.Entry<Integer, Double>> req_cache) {
        this.id_questionnaire = id_questionnaire;
        this.m_req_cache = req_cache;
    }
    public List<Map.Entry<Integer, Double>> get_req_cache() {
        return m_req_cache;
    }
    public void setM_req_cache(List<Map.Entry<Integer, Double>> req_cache) {
        this.m_req_cache = req_cache;
    }
}
