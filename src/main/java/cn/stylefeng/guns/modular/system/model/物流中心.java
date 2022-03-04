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
@TableName("egg_物流中心")
public class 物流中心 extends Model<物流中心> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String 物流中心名称;
    private String 地址;
    private String 电话号码;
    private String 邮编;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String get物流中心名称() {
        return 物流中心名称;
    }

    public void set物流中心名称(String 物流中心名称) {
        this.物流中心名称 = 物流中心名称;
    }

    public String get地址() {
        return 地址;
    }

    public void set地址(String 地址) {
        this.地址 = 地址;
    }

    public String get电话号码() {
        return 电话号码;
    }

    public void set电话号码(String 电话号码) {
        this.电话号码 = 电话号码;
    }

    public String get邮编() {
        return 邮编;
    }

    public void set邮编(String 邮编) {
        this.邮编 = 邮编;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "物流中心{" +
        ", id=" + id +
        ", 物流中心名称=" + 物流中心名称 +
        ", 地址=" + 地址 +
        ", 电话号码=" + 电话号码 +
        ", 邮编=" + 邮编 +
        "}";
    }
}
