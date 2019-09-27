package atlan.ceer.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class TestController {
    @RequestMapping("/testParam")
    public Map testParam(HttpServletRequest request){
        Map map=request.getParameterMap();

        System.out.println(map.size());
        return map;
    }
    @RequestMapping("/testJson")
    public String testJson(@RequestBody String json){
        System.out.println(json);
        return json;
    }
}
