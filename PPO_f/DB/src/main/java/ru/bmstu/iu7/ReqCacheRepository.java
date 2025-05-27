package ru.bmstu.iu7;

import ru.bmstu.iu7.API.IReqCacheRepository;
import ru.bmstu.iu7.API.model.ReqCache;

import java.util.List;
import java.util.Map;

public class ReqCacheRepository implements IReqCacheRepository {
    @Override
    public ReqCache insert(Long id_quest, double harmonic_average_norm) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id_quest) throws Exception {

    }

    @Override
    public List<Map.Entry<Long, Double>> findAll(Long id_cur_quest) throws Exception{
        return null;
    }
}
