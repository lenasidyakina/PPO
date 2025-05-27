package ru.bmstu.iu7.API;

import ru.bmstu.iu7.API.model.ReqCache;

import java.util.List;
import java.util.Map;

public interface IReqCacheRepository {
    ReqCache insert(Long id_quest, double harmonic_average_norm) throws Exception;
    void delete(Long id_quest) throws Exception;
    List<Map.Entry<Long, Double>> findAll(Long id_cur_quest) throws Exception;
}
