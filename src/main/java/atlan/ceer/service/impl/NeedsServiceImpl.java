package atlan.ceer.service.impl;

import atlan.ceer.mapper.NeedsMapper;
import atlan.ceer.mapper.QueryMapper;
import atlan.ceer.model.MyResult;
import atlan.ceer.model.NeedsInf;
import atlan.ceer.pojo.Needs;
import atlan.ceer.service.NeedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NeedsServiceImpl implements NeedsService {
    @Autowired
    private NeedsMapper needsMapper;
    @Autowired
    private QueryMapper queryMapper;
    @Override
    public MyResult addNeeds(Needs needs) {
        try {
            needsMapper.insert(needs);
            return new MyResult(needs.getNeedsid(),true,"添加成功","200");
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"添加失败","201");
        }
    }

    @Override
    public MyResult queryNeeds(String id) {
        try {
            NeedsInf needsInf = queryMapper.queryNeedsInf(id);
            return new MyResult(needsInf,true,"查询成功","200");
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"查询失败(数据库)","202");
        }
    }

    @Override
    public MyResult queryNeedsList(Map map) {
        try {
            List<NeedsInf> needsInfs=queryMapper.queryNeedsInfList(map);
            return new MyResult(needsInfs,true,"查询成功","200");
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"查询失败(数据库)","202");
        }
    }
}
