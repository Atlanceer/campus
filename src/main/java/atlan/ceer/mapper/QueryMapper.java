package atlan.ceer.mapper;

import atlan.ceer.model.NeedsInf;
import atlan.ceer.model.SimpleGoods;
import atlan.ceer.model.UserInfAll;

import java.util.List;
import java.util.Map;

public interface QueryMapper {
    List<SimpleGoods> queryGoodsList(Map map);
    UserInfAll queryUserInfAll(String userid);
    List<NeedsInf> queryNeedsInfList(Map map);
    NeedsInf queryNeedsInf(String needsid);
}
