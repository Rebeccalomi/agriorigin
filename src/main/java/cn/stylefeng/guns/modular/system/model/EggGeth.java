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
 * @since 2020-05-03
 */
@TableName("egg_geth")
public class EggGeth extends Model<EggGeth> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String geth;
    private String info;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGeth() {
        return geth;
    }

    public void setGeth(String geth) {
        this.geth = geth;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EggGeth{" +
        ", id=" + id +
        ", geth=" + geth +
        ", info=" + info +
        "}";
    }

}
