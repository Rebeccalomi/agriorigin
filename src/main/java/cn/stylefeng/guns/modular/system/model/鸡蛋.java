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
@TableName("egg_鸡蛋")
public class 鸡蛋 extends Model<鸡蛋> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String uuid;
    private String 货物名称;
    private String 货物重量;
    private String 货物体积;
    private Long 生产商编号;
    private String 存放地点;
    private String 库存;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String get货物名称() {
        return 货物名称;
    }

    public void set货物名称(String 货物名称) {
        this.货物名称 = 货物名称;
    }

    public String get货物重量() {
        return 货物重量;
    }

    public void set货物重量(String 货物重量) {
        this.货物重量 = 货物重量;
    }

    public String get货物体积() {
        return 货物体积;
    }

    public void set货物体积(String 货物体积) {
        this.货物体积 = 货物体积;
    }

    public Long get生产商编号() {
        return 生产商编号;
    }

    public void set生产商编号(Long 生产商编号) {
        this.生产商编号 = 生产商编号;
    }

    public String get存放地点() {
        return 存放地点;
    }

    public void set存放地点(String 存放地点) {
        this.存放地点 = 存放地点;
    }

    public String get库存() {
        return 库存;
    }

    public void set库存(String 库存) {
        this.库存 = 库存;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "鸡蛋{" +
        ", id=" + id +
        ", 货物名称=" + 货物名称 +
        ", 货物重量=" + 货物重量 +
        ", 货物体积=" + 货物体积 +
        ", 生产商编号=" + 生产商编号 +
        ", 存放地点=" + 存放地点 +
        ", 库存=" + 库存 +
        "}";
    }
}
