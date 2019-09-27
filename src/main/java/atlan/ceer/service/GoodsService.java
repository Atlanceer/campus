package atlan.ceer.service;

import atlan.ceer.model.MyResult;
import atlan.ceer.pojo.Goodses;

public interface GoodsService {
    MyResult addGoods(Goodses goodses);
    MyResult queryGoods(String id);
    MyResult queryGoodsList();
    MyResult deleteGoods(String userid,String goodsid);
}
