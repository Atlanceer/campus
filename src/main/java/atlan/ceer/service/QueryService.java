package atlan.ceer.service;

import atlan.ceer.model.MyResult;
import atlan.ceer.pojo.User;

import java.util.Map;

public interface QueryService {
    MyResult queryGoodsList(Map map);
    User queryUserInf(String id);
}
