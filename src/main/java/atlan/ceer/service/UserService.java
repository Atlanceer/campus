package atlan.ceer.service;

import atlan.ceer.model.MyResult;
import atlan.ceer.pojo.User;

public interface UserService {
    MyResult addUser(User user);
    MyResult loginJudge(User user);
    MyResult updateUser(User user);
    boolean judgePhone(String phone);
    MyResult queryUserInfAll(String userid);
    boolean insertIntoUserInfDefault(String userid);
    boolean checkLoginStatus(String userid, String token);
}
