package ru.bmstu.iu7;

import ru.bmstu.iu7.API.IReqCacheRepository;
import ru.bmstu.iu7.API.IUserRepository;
import ru.bmstu.iu7.API.model.Information;
import ru.bmstu.iu7.API.model.Questionnaire;
import ru.bmstu.iu7.API.model.ReqCache;

import java.util.List;
import java.util.Map;

public class ReqCacheRepository implements IReqCacheRepository {
    @Override
    public ReqCache insert(int id_quest, double harmonic_average_norm) throws Exception {
        return null;
    }
    @Override
    public List<Map.Entry<Integer, Double>> findAll(int id_cur_quest) throws Exception{
        return null;
    }
}
