package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2020-01-16
 */
@TableName("egg_仓库")
public class 仓库 extends Model<仓库> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long 所属物流中心编号;
    private String 仓库地址;
    private String 仓库性质;
    private String 仓库总量;
    private String 电话;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long get所属物流中心编号() {
        return 所属物流中心编号;
    }

    public void set所属物流中心编号(Long 所属物流中心编号) {
        this.所属物流中心编号 = 所属物流中心编号;
    }

    public String get仓库地址() {
        return 仓库地址;
    }

    public void set仓库地址(String 仓库地址) {
        this.仓库地址 = 仓库地址;
    }

    public String get仓库性质() {
        return 仓库性质;
    }

    public void set仓库性质(String 仓库性质) {
        this.仓库性质 = 仓库性质;
    }

    public String get仓库总量() {
        return 仓库总量;
    }

    public void set仓库总量(String 仓库总量) {
        this.仓库总量 = 仓库总量;
    }

    public String get电话() {
        return 电话;
    }

    public void set电话(String 电话) {
        this.电话 = 电话;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "仓库{" +
        ", id=" + id +
        ", 所属物流中心编号=" + 所属物流中心编号 +
        ", 仓库地址=" + 仓库地址 +
        ", 仓库性质=" + 仓库性质 +
        ", 仓库总量=" + 仓库总量 +
        ", 电话=" + 电话 +
        "}";
    }
}
