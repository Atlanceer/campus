package demo;

import atlan.ceer.mapper.GoodsesMapper;
import atlan.ceer.mapper.UserMapper;
import atlan.ceer.pojo.Goodses;
import atlan.ceer.pojo.User;
import atlan.ceer.pojo.UserExample;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })

public class mapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsesMapper goodsesMapper;
    @Test
    public void test1(){
        UserExample ex=new UserExample();
        UserExample.Criteria criteria=ex.createCriteria();
        criteria.andUseridEqualTo("1111111111");
        List<User> users=userMapper.selectByExample(ex);

        ex.clear();
        UserExample.Criteria criteria2=ex.createCriteria();
        criteria2.andUseridEqualTo("1111111111111111");
        users=userMapper.selectByExample(ex);
        if (users.isEmpty()){
            System.out.println("为空");
        }else {
            System.out.println("不为空");
        }
    }
    @Test
    public void test2(){
        Goodses goodses=goodsesMapper.selectByPrimaryKey("de4741a288e64d2883ab65cea99122b1");
        System.out.println(goodses.getImages());
        JSONArray jsonArray=new JSONArray(goodses.getImages());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("images",jsonArray);
        System.out.println(jsonObject.toString());
    }
}
