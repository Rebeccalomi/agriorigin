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
@TableName("egg_生产商")
public class 生产商 extends Model<生产商> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String 生产商名称;
    private String 联系人;
    private String 生产商地址;
    private String 联系电话;
    private String 邮编;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String get生产商名称() {
        return 生产商名称;
    }

    public void set生产商名称(String 生产商名称) {
        this.生产商名称 = 生产商名称;
    }

    public String get联系人() {
        return 联系人;
    }

    public void set联系人(String 联系人) {
        this.联系人 = 联系人;
    }

    public String get生产商地址() {
        return 生产商地址;
    }

    public void set生产商地址(String 生产商地址) {
        this.生产商地址 = 生产商地址;
    }

    public String get联系电话() {
        return 联系电话;
    }

    public void set联系电话(String 联系电话) {
        this.联系电话 = 联系电话;
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
        return "生产商{" +
        ", id=" + id +
        ", 生产商名称=" + 生产商名称 +
        ", 联系人=" + 联系人 +
        ", 生产商地址=" + 生产商地址 +
        ", 联系电话=" + 联系电话 +
        ", 邮编=" + 邮编 +
        "}";
    }
}
