package atlan.ceer.controller;

import atlan.ceer.mapper.QueryMapper;
import atlan.ceer.model.GoodsInf;
import atlan.ceer.model.MyResult;
import atlan.ceer.model.SimpleGoods;
import atlan.ceer.model.UserInfAll;
import atlan.ceer.pojo.Goodses;
import atlan.ceer.pojo.User;
import atlan.ceer.service.GoodsService;
import atlan.ceer.service.QueryService;
import atlan.ceer.service.UserService;
import atlan.ceer.util.CodeUtil;
import atlan.ceer.util.TimeUtil;
import atlan.ceer.util.TokenUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Value("${headPath}")
    private String headPath;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private CodeUtil codeUtil;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private TimeUtil timeUtil;
    @Autowired
    private QueryService queryService;
    @Autowired
    private UserService userService;
    @RequestMapping("/add")
    public MyResult addGoods(@RequestBody String addGoods, HttpServletRequest request){
        System.out.println(addGoods);
        try {
            JSONObject goods=new JSONObject(addGoods);
            String token=request.getHeader("token");
            Map map=tokenUtil.parseToken(token);
            //添加id
            Goodses goodses=new Goodses();
            goodses.setUserid((String) map.get("id"));
            goodses.setGoodsid(codeUtil.getUUID());
            goodses.setGoodsname(goods.getString("goodsname"));
            goodses.setMainimage(goods.getString("mainimage"));
            goodses.setGoodsinf(goods.getString("goodsinformation"));
            goodses.setLocation(goods.getString("location"));
            goodses.setPrice(Double.valueOf(goods.getString("price")));
            goodses.setCategory(goods.getString("category"));
            goodses.setIsnew(goods.getString("isnew"));
            goodses.setTag(goods.getString("tag"));
            goodses.setImages(goods.getJSONArray("images").toString());
            goodses.setCreatetime(timeUtil.getTime());
            goodses.setCollect(0);
            goodses.setBrowse(0);
            return goodsService.addGoods(goodses);
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"服务器错误(格式出错)","202");
        }

    }

    //商品详情
    @RequestMapping("/GoodsInf")
    public MyResult queryGoods(@RequestBody String body){
        MyResult myResult= null;
        try {
            System.out.println(body);
            JSONObject jsonObject=new JSONObject(body);
            String id=null;
            //可以传id或者goodsid
            if (jsonObject.has("id")){
                id=jsonObject.getString("id");
            }else if (jsonObject.has("goodsid")){
                id=jsonObject.getString("goodsid");
            }else {
                return new MyResult(false,"格式错误","201");
            }
            myResult = goodsService.queryGoods(id);
            if (myResult.isStatus()){
                Goodses goodses= (Goodses) myResult.getContent();

                GoodsInf goodsInf=new GoodsInf();
                goodsInf.setBrowse(goodses.getBrowse());
                goodsInf.setCategory(goodses.getCategory());
                goodsInf.setCollect(goodses.getCollect());
                goodsInf.setUserid(goodses.getUserid());
                goodsInf.setCreatetime(goodses.getCreatetime());
                goodsInf.setGoodsid(goodses.getGoodsid());
                goodsInf.setGoodsname(goodses.getGoodsname());
                goodsInf.setGoodsinf(goodses.getGoodsinf());
                goodsInf.setIsnew(goodses.getIsnew());
                goodsInf.setLocation(goodses.getLocation());
                goodsInf.setMainimage(headPath+goodses.getMainimage());
                goodsInf.setPrice(goodses.getPrice());
                goodsInf.setTag(goodses.getTag());

                //User user=queryService.queryUserInf(goodses.getUserid());
                UserInfAll userInfAll= (UserInfAll) userService.queryUserInfAll(goodses.getUserid()).getContent();
                goodsInf.setUsername(userInfAll.getUsername());
                goodsInf.setAvatar(userInfAll.getAvatar());

                System.out.println(goodses.toString());
                //图片数组
                JSONArray imagesArray=new JSONArray(goodses.getImages());
                //遍历数组添加访问路径头
                for (int i=0;i<imagesArray.length();i++){
                    imagesArray.put(i,headPath+imagesArray.getString(i));
                }
                List list=imagesArray.toList();
                goodsInf.setImages(list);
                //goodses.setImages(list.toString());
                myResult.setContent(goodsInf);
            }
            return myResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"格式错误","201");
        }
    }

    //商品列表（安新鲜排序）
    @RequestMapping("/queryGoodsList")
    public MyResult queryGoodsList(@RequestBody String body){
        try {
            //获取前端传来的json数据
            JSONObject jsonObject=new JSONObject(body);
            //SimpleGoods simpleGoods=new SimpleGoods();
            int page=jsonObject.getInt("page");
            String location=null;
            //判断是否有location字段
            if (jsonObject.has("location")){
                location=jsonObject.getString("location");
                if (location.equals("all")||location.equals("")||location.equals("ALL")){
                    location=null;
                }else if (location.equals("A")||location.equals("C")||location.equals("B")){
                    //如果前端传来字母就转化为中文
                    if (location.equals("A")){
                        location="宜宾校区";
                    }else if (location.equals("B")){
                        location="汇南校区";
                    }
                    if (location.equals("C")){
                        location="营盘校区";
                    }
                }else {
                    System.out.println("cuow 1111");
                    return new MyResult(false,"服务器错误(格式错误)","201");
                }
            }
            String tag=null;
            if (jsonObject.has("tag")){
                tag=jsonObject.getString("tag");
                if (tag.equals("all")||tag.equals("")||tag.equals("ALL")){
                    tag=null;
                }/*else {
                    System.out.println("cuow 222");
                    return new MyResult(false,"服务器错误(格式错误)","201");
                }*/
            }
            //添加进map
            Map<String,Object> map=new HashMap<>();
            map.put("location",location);
            map.put("tag",tag);
            if (page!=0){
                page=page*20;
            }
            map.put("page",page);
            //查询最新商品
            MyResult myResult=queryService.queryGoodsList(map);
            /*if (myResult.isStatus()){
                //System.out.println(myResult.getContent().toString());
                List<SimpleGoods> simpleGoodsList= (List<SimpleGoods>) myResult.getContent();
                Iterator iterator=simpleGoodsList.iterator();
                while (iterator.hasNext()){
                    SimpleGoods simpleGoods= (SimpleGoods) iterator.next();
                    System.out.println(simpleGoods.toString());
                    User user=queryService.queryUserInf(simpleGoods.getUserid());
                    System.out.println(user.toString());
                }
            }*/
            return myResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"服务器错误(格式错误)","201");
        }
    }
    //删除商品
    @RequestMapping("/delete")
    public MyResult deleteGoods(@RequestBody String body,HttpServletRequest request){
        try {
            JSONObject jsonObject=new JSONObject(body);
            String goodsId=null;
            //得到token获取用户id
            String token=request.getHeader("token");
            Map map=tokenUtil.parseToken(token);
            String userid= (String) map.get("id");
            if (jsonObject.has("goodsid")){
                goodsId=jsonObject.getString("goodsid");
            }else if (jsonObject.has("id")){
                goodsId=jsonObject.getString("id");
            }else {
                return new MyResult(false,"格式错误","201");
            }
            return goodsService.deleteGoods(userid,goodsId);
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"格式错误","201");
        }
    }
}
