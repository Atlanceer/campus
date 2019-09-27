package atlan.ceer.controller;

import atlan.ceer.model.MyResult;
import atlan.ceer.pojo.User;
import atlan.ceer.service.UserService;
import atlan.ceer.util.CodeUtil;
import atlan.ceer.util.MessageUtil;
import atlan.ceer.util.TokenUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private CodeUtil codeUtil;
    @Autowired
    private TokenUtil tokenUtil;

    @RequestMapping("/register")
    public MyResult register(@RequestBody String body,HttpServletRequest request,HttpServletResponse response){
        JSONObject jsonObject=new JSONObject(body);
        String code=jsonObject.getString("code");
        String phone=jsonObject.getString("phone");
        String password=jsonObject.getString("password");
        User user=new User();
        user.setPhone(phone);
        user.setPassword(password);

        //TokenUtil tokenUtil=new TokenUtil();
        System.out.println(user.toString());
        String codeToken=request.getHeader("codeToken");
        //验证码验证
        MyResult myResult=new MyResult();
        //token验证
        if (tokenUtil.verifyToken(codeToken)){
            //验证码是否匹配
            if (code.equals(tokenUtil.parseCodeToken(codeToken))){
                //匹配
                myResult=userService.addUser(user);
                response.setHeader("token",myResult.getContent().toString());
                myResult.setContent("");
            }else {
                myResult.setStatus(false);
                myResult.setMessage("验证码错误");
                myResult.setCode("202");
            }
        }else {
            myResult.setStatus(false);
            myResult.setMessage("验证码失效");
            myResult.setCode("204");

        }
        return myResult;
    }

    @RequestMapping("/login")
    public MyResult login(@RequestBody User user,HttpServletResponse response){
        System.out.println(user);
        //user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        //MyResult myResult = userService.loginJudge(user);
        MyResult myResult=userService.loginJudge(user);
        if (myResult.getContent()!=null){
            response.setHeader("token",myResult.getContent().toString());
        }
        myResult.setContent("");
        return myResult;
    }

    @RequestMapping("/test")
    public String test(@RequestBody String str){
        System.out.println(str);
        return str;
    }

    @RequestMapping("/getMessage")
    public MyResult sendMessage(@RequestBody String body, HttpServletResponse response,HttpServletRequest request){
        //System.out.println(body);
        //得到请求体
        JSONObject jsonObject=new JSONObject(body);
        String phone=jsonObject.getString("phone");
        if (userService.judgePhone(phone)){
            return new MyResult(false,"手机号存在请直接登录","201");
        }
        String auth=request.getHeader("auth");
        //判断请求是否合法
        StringBuilder toMD5=new StringBuilder(phone.substring(7));
        toMD5.append("a");
        if (!auth.equals(DigestUtils.md5DigestAsHex(toMD5.toString().getBytes()))){
            return new MyResult(false,"非法请求","205");
        }
        //System.out.println(phone);
        //MyResult myResult=new MyResult();
        try {
            //发送短信
            //MessageUtil messageUtil=new MessageUtil();
            //TokenUtil tokenUtil=new TokenUtil();
            //发送短信
            //String code=messageUtil.sendRegisterMessage(phone);
            String code="000000";
            if (code==null){
                return new MyResult(false,"发送失败","206");
            }
            String codeToken=tokenUtil.createMessageToken(code);
            response.setHeader("codeToken",codeToken);
            return new MyResult(true,"发送成功","200");
        } catch (Exception e) {
            e.printStackTrace();
            //myResult.setStatus(false);
            return new MyResult(false,"发送失败","206");
        }
    }

    @RequestMapping("/update")
    public MyResult updateUser(@RequestBody String body,HttpServletResponse response,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject(body);
        String phone=jsonObject.getString("phone");
        //如果密码为空就发送短信
        if (!jsonObject.has("password")){
            //发送短信
            //String sendCode=messageUtil.sendRegisterMessage(phone);
            String sendCode="000000";
            if (sendCode==null){
                return new MyResult(false,"发送失败","206");
            }else {
                String codeToken=tokenUtil.createMessageToken(sendCode);
                response.setHeader("codeToken",codeToken);
                return new MyResult(true,"发送成功","200");
            }
        }else {
            String password=jsonObject.getString("password");
            String code=jsonObject.getString("code");

            String codeToken=request.getHeader("codeToken");
            //String token=request.getHeader("token");
            MyResult myResult=new MyResult();
            //token验证
            if (tokenUtil.verifyToken(codeToken)){
                //验证码是否匹配
                if (code.equals(tokenUtil.parseCodeToken(codeToken))){
                    //匹配
                    //Map map=tokenUtil.parseToken(token);
                    //String id= (String) map.get("id");
                    User user=new User();
                    //user.setUserid(id);
                    user.setPhone(phone);
                    user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
                    myResult=userService.updateUser(user);
                    /*myResult.setCode("200");
                    myResult.setStatus(true);*/
                }else {
                    myResult.setStatus(false);
                    myResult.setMessage("验证码错误");
                    myResult.setCode("202");
                }
            }else {
                myResult.setStatus(false);
                myResult.setMessage("验证码失效");
                myResult.setCode("204");
            }
            return myResult;
        }
    }
    @RequestMapping("/userInfAll")
    public MyResult queryUserInfAll(@RequestBody String body){
        try {
            JSONObject jsonObject=new JSONObject(body);
            String userid=jsonObject.getString("userid");
            return userService.queryUserInfAll(userid);
        } catch (JSONException e) {
            e.printStackTrace();
            return new MyResult(false,"格式错误","201");
        }
    }
    @RequestMapping(value = "/checkLoginStatus", method = RequestMethod.GET)
    public MyResult checkLoginStatus(HttpServletRequest request){
        try {
            String token=request.getHeader("token");
            if (!tokenUtil.verifyToken(token)){
                return new MyResult(false,"请重新登录(过期)","201");
            }
            Map map=tokenUtil.parseToken(token);
            String userid= (String) map.get("id");
            if (userService.checkLoginStatus(userid,token)){
                return new MyResult(true,"你已登录","200");
            }else {
                return new MyResult(false,"请重新登录","201");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"请求格式出错","202");
        }
    }

}
