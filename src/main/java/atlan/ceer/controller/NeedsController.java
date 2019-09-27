package atlan.ceer.controller;

import atlan.ceer.model.MyResult;
import atlan.ceer.pojo.Needs;
import atlan.ceer.service.NeedsService;
import atlan.ceer.util.CodeUtil;
import atlan.ceer.util.TimeUtil;
import atlan.ceer.util.TokenUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/needs")
@RestController
public class NeedsController {
    @Autowired
    private NeedsService needsService;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private CodeUtil codeUtil;
    @Autowired
    private TimeUtil timeUtil;

    @RequestMapping("/add")
    public MyResult addNeeds(@RequestBody String addNeeds, HttpServletRequest request){
        try {
            JSONObject jsonObject=new JSONObject(addNeeds);
            Needs needs=new Needs();
            String token=request.getHeader("token");
            Map map=tokenUtil.parseToken(token);
            needs.setUserid((String) map.get("id"));
            needs.setNeedsid(codeUtil.getUUID());
            needs.setGoodsname(jsonObject.getString("goodsname"));
            needs.setInformation(jsonObject.getString("information"));
            needs.setCategory(jsonObject.getString("category"));
            needs.setLocation(jsonObject.getString("location"));
            needs.setPrice(Double.valueOf(jsonObject.getString("price")));
            needs.setTip(Double.valueOf(jsonObject.getString("tip")));
            needs.setCreatetime(timeUtil.getTime());
            return needsService.addNeeds(needs);
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"服务器错误(格式出错)","202");
        }
    }

    @RequestMapping("/queryNeedsList")
    public MyResult queryNeedsList(@RequestBody String body){
        //获取前端传来的json数据
        JSONObject jsonObject=new JSONObject(body);
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
                return new MyResult(false,"服务器错误(格式错误)","201");
            }
        }
        //添加进map
        Map<String,Object> map=new HashMap<>();
        map.put("location",location);
        if (page!=0){
            page=page*20;
        }
        map.put("page",page);
        //查询最新商品
        return needsService.queryNeedsList(map);

    }

    @RequestMapping("/needsInf")
    public MyResult queryNeedsInf(@RequestBody String body){
        JSONObject jsonObject=new JSONObject(body);
        String needsid=null;
        if (jsonObject.has("id")){
            needsid=jsonObject.getString("id");
        }else if(jsonObject.has("needsid")){
            needsid=jsonObject.getString("needsid");
        }else {
            return new MyResult(false,"查询失败(格式错误)","201");
        }
        return needsService.queryNeeds(needsid);
    }

}
