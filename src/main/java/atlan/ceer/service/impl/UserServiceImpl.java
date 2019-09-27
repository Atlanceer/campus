package atlan.ceer.service.impl;

import atlan.ceer.mapper.QueryMapper;
import atlan.ceer.mapper.UserMapper;
import atlan.ceer.mapper.UserinfMapper;
import atlan.ceer.model.MyResult;
import atlan.ceer.model.UserInfAll;
import atlan.ceer.pojo.User;
import atlan.ceer.pojo.UserExample;
import atlan.ceer.pojo.UserinfWithBLOBs;
import atlan.ceer.service.UserService;
import atlan.ceer.util.CodeUtil;
import atlan.ceer.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Value("${defaultAvatarPath}")
    private String avatar;
    @Value("${headPath}")
    private String headPath;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private QueryMapper queryMapper;
    @Autowired
    private UserinfMapper userinfMapper;

    @Transactional
    @Override
    public MyResult addUser(User user) {
        CodeUtil codeUtil=new CodeUtil();
        MyResult myResult=new MyResult();
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andPhoneEqualTo(user.getPhone());
        List<User> users=userMapper.selectByExample(userExample);
        //手机号不存在
        if (users.isEmpty()){
            user.setUsername(codeUtil.getUserName());
            user.setUserid(codeUtil.getCode());
            try {
                //密码加密
                user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                userMapper.insert(user);
                //查询用户的信息用于生成token
                userExample.clear();
                UserExample.Criteria criteria1=userExample.createCriteria();
                criteria1.andPhoneEqualTo(user.getPhone());
                List<User> userList=userMapper.selectByExample(userExample);
                String token=tokenUtil.createToken(userList.get(0).getUsername(),userList.get(0).getUserid());
                //将token存入数据库
                User addToken=new User();
                addToken.setToken(token);
                userMapper.updateByExampleSelective(addToken,userExample);

                //同时在userinf表中添加记录
                UserinfWithBLOBs userinf=new UserinfWithBLOBs();
                userinf.setUserid(user.getUserid());
                //默认头像
                userinf.setAvatar(avatar);
                userinfMapper.insert(userinf);


                return new MyResult(token,true,"注册成功","203");
            } catch (Exception e) {
                e.printStackTrace();
                myResult.setMessage("添加失败");
                myResult.setStatus(false);
                myResult.setCode("207");
                return myResult;
            }
        }else {
            myResult.setMessage("该手机号已被注册");
            myResult.setStatus(false);
            myResult.setCode("201");
            return myResult;
        }
    }

    @Override
    public MyResult loginJudge(User user) {
        UserExample userExample=new UserExample();
        //判断什么方式登录
        UserExample.Criteria criteria=userExample.createCriteria();
        if (user.getUsername()!=null){
            criteria.andUsernameEqualTo(user.getUsername());
        }else if (user.getEmail()!=null){
            criteria.andEmailEqualTo(user.getEmail());
        }else if (user.getPhone()!=null){
            criteria.andPhoneEqualTo(user.getPhone());
            //criteria.andPasswordEqualTo(user.getPassword());
        }
        MyResult result=new MyResult();
        if (userMapper.countByExample(userExample)!=1){
            //System.out.println("手机号不存在");
            return new MyResult(false,"手机号或用户名不存在","202");
        }else {
            criteria.andPasswordEqualTo(user.getPassword());
        }
        if (userMapper.countByExample(userExample)!=1){
            //System.out.println("密码错误");
            result.setStatus(false);
            result.setMessage("密码错误");
            result.setCode("201");
            return result;
        }else {
            try {
                //System.out.println("成功");
                userExample.clear();
                UserExample.Criteria criteria1=userExample.createCriteria();
                criteria1.andPhoneEqualTo(user.getPhone());
                List<User> users=userMapper.selectByExample(userExample);
                //登录成功返回token
                //TokenUtil tokenUtil=new TokenUtil();
                //token里面存有用户的id和用户名
                String token=tokenUtil.createToken(users.get(0).getUsername(),users.get(0).getUserid());
                //将token存入数据库
                User addToken=new User();
                addToken.setToken(token);
                userMapper.updateByExampleSelective(addToken,userExample);
                result.setStatus(true);
                result.setCode("200");
                result.setMessage("登录成功");
                result.setContent(token);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                result.setStatus(false);
                result.setCode("206");
                return result;
            }
        }
    }

    @Override
    public MyResult updateUser(User user) {
        try {
            UserExample userExample=new UserExample();
            UserExample.Criteria criteria=userExample.createCriteria();
            criteria.andPhoneEqualTo(user.getPhone());
            userMapper.updateByExampleSelective(user,userExample);
            return new MyResult(true,"修改成功","202");
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"数据库错误","205");
        }
    }

    @Override
    public boolean judgePhone(String phone) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<User> users=userMapper.selectByExample(userExample);
        //手机号不存在返回false
        if (users.isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public MyResult queryUserInfAll(String userid) {
        try {
            UserInfAll userInfAll=queryMapper.queryUserInfAll(userid);
            if (userInfAll.getAvatar().equals(headPath)){
                userInfAll.setAvatar(avatar);
            }
            return new MyResult(userInfAll,true,"查询成功","200");
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResult(false,"查询失败(数据库)","202");
        }
    }

    @Override
    public boolean insertIntoUserInfDefault(String userid) {
        try {
            UserinfWithBLOBs userinf=new UserinfWithBLOBs();
            userinf.setUserid(userid);
            //默认头像
            userinf.setAvatar(avatar);
            userinfMapper.insert(userinf);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkLoginStatus(String userid,String token){
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUseridEqualTo(userid);
        criteria.andTokenEqualTo(token);
        //查询用户token是否过期或者伪造登录
        long count=userMapper.countByExample(userExample);
        System.out.println(count+"===="+userid+"===="+token);
        return !(count==0);
    }

}
