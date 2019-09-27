package atlan.ceer.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("messageUtil")
public class MessageUtil {
    private static final int appid=1400241016;
    private static final String appkey="4fc4d51c1eccfd46d57fe09dbe5b2803";
    private static final int templateId = 393363;//模板id 登录
    private static final String smsSign="有头有脑公众号";//签名
    @Autowired
    private CodeUtil codeUtil;

    //发送短信注册用
    public String sendRegisterMessage(String phoneNum){
        //CodeUtil codeUtil=new CodeUtil();
        String code=codeUtil.getMessageCode();
        String[] params = {code,"6"};
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNum,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            JSONObject jsonObject=new JSONObject(result.toString());
            //System.out.println(jsonObject.toString());
            //发送失败返回null
            if (jsonObject.getInt("result")!=0){
                return null;
            }
            System.out.println(result);
        } catch (HTTPException | IOException e) {
            e.printStackTrace();
        }
        return code;
    }
}
