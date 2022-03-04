package cn.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2020-01-16
 */
@TableName("egg_提取")
public class 提取 extends Model<提取> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String uuid;
    private Long 物流中心编号;
    private Long 仓库编号;
    private String 检测结果;
    private Date 时间;
    private Boolean 是否流入市场;
    private String 仓库地址;
    private String 物流中心名称;
    private String geth;

    public String getGeth() {
        return geth;
    }

    public void setGeth(String geth) {
        this.geth = geth;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String get仓库地址() {
        return 仓库地址;
    }

    public void set仓库地址(String 仓库地址) {
        this.仓库地址 = 仓库地址;
    }

    public String get物流中心名称() {
        return 物流中心名称;
    }

    public void set物流中心名称(String 物流中心名称) {
        this.物流中心名称 = 物流中心名称;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long get物流中心编号() {
        return 物流中心编号;
    }

    public void set物流中心编号(Long 物流中心编号) {
        this.物流中心编号 = 物流中心编号;
    }

    public Long get仓库编号() {
        return 仓库编号;
    }

    public void set仓库编号(Long 仓库编号) {
        this.仓库编号 = 仓库编号;
    }

    public String get检测结果() {
        return 检测结果;
    }

    public void set检测结果(String 检测结果) {
        this.检测结果 = 检测结果;
    }

    public Date get时间() {
        return 时间;
    }

    public void set时间(Date 时间) {
        this.时间 = 时间;
    }

    public Boolean get是否流入市场() {
        return 是否流入市场;
    }

    public void set是否流入市场(Boolean 是否流入市场) {
        this.是否流入市场 = 是否流入市场;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "提取{" +
        ", id=" + id +
        ", 物流中心编号=" + 物流中心编号 +
        ", 仓库编号=" + 仓库编号 +
        ", 检测结果=" + 检测结果 +
        ", 时间=" + 时间 +
        ", 是否流入市场=" + 是否流入市场 +
        "}";
    }
}
