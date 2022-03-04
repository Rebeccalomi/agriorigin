package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.提取;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2020-01-16
 */
@Repository
public interface 提取Mapper extends BaseMapper<提取> {
    List<提取> getList1(提取 提取);
    List<提取> getList2(提取 提取);
    List<提取> getList3(提取 提取);
    boolean update123(提取 提取);
}
