package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.提取;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2020-01-16
 */
public interface I提取Service extends IService<提取> {
        List<提取> getList1(提取 提取);
        List<提取> getList2(提取 提取);
        List<提取> getList3(提取 提取);
        boolean update123(提取 提取);
}
