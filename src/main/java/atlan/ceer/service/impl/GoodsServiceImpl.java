package atlan.ceer.service.impl;

import atlan.ceer.mapper.GoodsesMapper;
import atlan.ceer.model.MyResult;
import atlan.ceer.pojo.Goodses;
import atlan.ceer.pojo.GoodsesExample;
import atlan.ceer.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsesMapper goodsesMapper;
    @Override
    public MyResult addGoods(Goodses goodses) {
        try {
            goodsesMapper.insert(goodses);
            return new MyResult(goodses.getGoodsid(),true,"添加成功","200");
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"添加失败","201");
        }
    }

    @Override
    public MyResult queryGoods(String id) {
        try {
            Goodses goodses=goodsesMapper.selectByPrimaryKey(id);
            return new MyResult(goodses,true,"查询成功","200");
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"查询失败","201");
        }
    }

    @Override
    public MyResult queryGoodsList() {
        return null;
    }

    @Transactional
    @Override
    public MyResult deleteGoods(String userid, String goodsid) {
        try {
            GoodsesExample goodsesExample=new GoodsesExample();
            GoodsesExample.Criteria criteria=goodsesExample.createCriteria();
            criteria.andUseridEqualTo(userid);
            criteria.andGoodsidEqualTo(goodsid);
            goodsesMapper.deleteByExample(goodsesExample);
            return new MyResult(true,"删除成功","200");
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"删除失败(数据库)","202");
        }
    }
}
