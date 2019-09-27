package atlan.ceer.service;

import atlan.ceer.model.MyResult;
import atlan.ceer.pojo.Needs;

import java.util.Map;

public interface NeedsService {
    MyResult addNeeds(Needs needs);
    MyResult queryNeeds(String id);
    MyResult queryNeedsList(Map<String,Object> map);
}
