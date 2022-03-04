package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.提取;
import cn.stylefeng.guns.modular.system.dao.提取Mapper;
import cn.stylefeng.guns.modular.system.service.I提取Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2020-01-16
 */
@Service
public class 提取ServiceImpl extends ServiceImpl<提取Mapper, 提取> implements I提取Service {
    @Resource
    private 提取Mapper 提取Mapper;
    @Override
    public List<提取> getList1(提取 提取) {
        return 提取Mapper.getList1(提取);
    }
    public List<提取> getList2(提取 提取) {
        return 提取Mapper.getList2(提取);
    }

    public List<提取> getList3(提取 提取) {
        return 提取Mapper.getList3(提取);
    }
    public boolean update123(提取 提取){
        return 提取Mapper.update123(提取);
    }
}
