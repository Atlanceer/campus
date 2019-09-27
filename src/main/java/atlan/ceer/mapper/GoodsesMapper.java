package atlan.ceer.mapper;

import atlan.ceer.pojo.Goodses;
import atlan.ceer.pojo.GoodsesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsesMapper {
    long countByExample(GoodsesExample example);

    int deleteByExample(GoodsesExample example);

    int deleteByPrimaryKey(String goodsid);

    int insert(Goodses record);

    int insertSelective(Goodses record);

    List<Goodses> selectByExampleWithBLOBs(GoodsesExample example);

    List<Goodses> selectByExample(GoodsesExample example);

    Goodses selectByPrimaryKey(String goodsid);

    int updateByExampleSelective(@Param("record") Goodses record, @Param("example") GoodsesExample example);

    int updateByExampleWithBLOBs(@Param("record") Goodses record, @Param("example") GoodsesExample example);

    int updateByExample(@Param("record") Goodses record, @Param("example") GoodsesExample example);

    int updateByPrimaryKeySelective(Goodses record);

    int updateByPrimaryKeyWithBLOBs(Goodses record);

    int updateByPrimaryKey(Goodses record);
}