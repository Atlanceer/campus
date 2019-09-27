package atlan.ceer.mapper;

import atlan.ceer.pojo.Userinf;
import atlan.ceer.pojo.UserinfExample;
import atlan.ceer.pojo.UserinfWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserinfMapper {
    long countByExample(UserinfExample example);

    int deleteByExample(UserinfExample example);

    int deleteByPrimaryKey(String userid);

    int insert(UserinfWithBLOBs record);

    int insertSelective(UserinfWithBLOBs record);

    List<UserinfWithBLOBs> selectByExampleWithBLOBs(UserinfExample example);

    List<Userinf> selectByExample(UserinfExample example);

    UserinfWithBLOBs selectByPrimaryKey(String userid);

    int updateByExampleSelective(@Param("record") UserinfWithBLOBs record, @Param("example") UserinfExample example);

    int updateByExampleWithBLOBs(@Param("record") UserinfWithBLOBs record, @Param("example") UserinfExample example);

    int updateByExample(@Param("record") Userinf record, @Param("example") UserinfExample example);

    int updateByPrimaryKeySelective(UserinfWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UserinfWithBLOBs record);

    int updateByPrimaryKey(Userinf record);
}