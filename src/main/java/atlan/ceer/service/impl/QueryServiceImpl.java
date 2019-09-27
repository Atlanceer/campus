package atlan.ceer.service.impl;

import atlan.ceer.mapper.GoodsesMapper;
import atlan.ceer.mapper.QueryMapper;
import atlan.ceer.mapper.UserMapper;
import atlan.ceer.model.MyResult;
import atlan.ceer.model.QueryPage;
import atlan.ceer.model.SimpleGoods;
import atlan.ceer.pojo.User;
import atlan.ceer.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryServiceImpl implements QueryService {
    @Autowired
    private QueryMapper queryMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsesMapper goodsesMapper;
    @Override
    public MyResult queryGoodsList(Map map) {
        try {
            //int page= (int) map.get("page");
            QueryPage<SimpleGoods> queryPage=new QueryPage<>();
            //总个数
            int totalCount= (int) goodsesMapper.countByExample(null);
            queryPage.setTotalCount(totalCount);
            //当前页
            queryPage.setCurrentPage((int) map.get("page"));
            int totalPage=0;
            if (totalCount%20==0&&totalCount!=0){
                totalPage=totalCount/20;
            }else {
                totalPage=totalCount/20+1;
            }
            queryPage.setTotalPage(totalPage);
            //查询发布的列表
            List<SimpleGoods> list = queryMapper.queryGoodsList(map);
            //当前查询的数量
            int listSize=list.size();
            queryPage.setThisPageCount(listSize);
            //判断以后是否还有页面
            if (listSize<20){
                queryPage.setHaveMore(false);
            }else {
                queryPage.setHaveMore(true);
            }
            queryPage.setPageList(list);
            return new MyResult(queryPage,true,"成功","200");
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"失败（数据库出错）","201");
        }
    }

    @Override
    public User queryUserInf(String id) {
        User user=userMapper.selectByPrimaryKey(id);
        return user;
    }
}
