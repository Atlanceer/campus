package demo;

import atlan.ceer.util.CodeUtil;
import atlan.ceer.util.MessageUtil;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class test1 {
    @Test
    public void test1(){
        UUID id=UUID.randomUUID();
        System.out.println(id.toString().replace("-",""));
    }
    /*@Test
    public void test2(){
        //String phoneNum="17828298850";
        String phoneNum="13108084439";
        String code="1123";
        String[] params = {"5678","5"};
        try {
            SmsSingleSender ssender = new SmsSingleSender(MessageUtil.appid, MessageUtil.appkey);
            //SmsSingleSenderResult result = ssender.send(0, "86", phoneNum,"【侧耳导航】您的验证码是: "+code+"请在三分钟之内输入", "", "");
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNum,
                    MessageUtil.templateId, params, MessageUtil.smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("发送成功");
    }*/
    @Test
    public void test3(){//可能生成四位数以下的数字
        int code= (int) (Math.random()*10000);
        System.out.println(code);
        CodeUtil codeUtil=new CodeUtil();
        System.out.println(codeUtil.getMessageCode());
    }
    @Test
    public void testMD5(){//测试token1
        String username="Atlan侧耳";
        StringBuilder test=new StringBuilder(username);
        UUID uuid=UUID.randomUUID();
        String code=uuid.toString().replace("-","");
        //StringBuffer toToken=new StringBuffer();
        StringBuilder toToken=new StringBuilder();
        toToken.append(username);
        toToken.append(code);
        String token=DigestUtils.md5DigestAsHex(toToken.toString().getBytes());
        System.out.println(token);
        //添加标识
        String temple=DigestUtils.appendMd5DigestAsHex(toToken.toString().getBytes(),test.append("-")).toString();
        System.out.println(temple);
    }
    @Test
    public void test4(){
        String phone="17828298855";
        String toMD5=phone.substring(7);
        System.out.println(toMD5);
        String test="8855a";
        String test2="123456";
        String code=DigestUtils.md5DigestAsHex(test.getBytes());
        System.out.println(code);
    }
    @Test
    public void testTime(){
        String path=new SimpleDateFormat("/yyyy/MM/dd").format(new Date(System.currentTimeMillis()));
        System.out.println(path);
    }

    @Test
    public void jsonTest(){
        String images="[1,2,3]";
        JSONObject jsonObject=new JSONObject();
        JSONArray objects=new JSONArray(images);
        jsonObject.put("images",objects);
        System.out.println(jsonObject.toString());
    }
}
