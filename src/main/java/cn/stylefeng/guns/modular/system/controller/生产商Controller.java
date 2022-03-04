package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.生产商;
import cn.stylefeng.guns.modular.system.service.I生产商Service;

/**
 * 生产商管理控制器
 *
 * @author fengshuonan
 * @Date 2020-01-16 14:11:24
 */
@Controller
@RequestMapping("/生产商")
public class 生产商Controller extends BaseController {

    private String PREFIX = "/system/生产商/";

    @Autowired
    private I生产商Service 生产商Service;

    /**
     * 跳转到生产商管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "生产商.html";
    }

    /**
     * 跳转到添加生产商管理
     */
    @RequestMapping("/生产商_add")
    public String 生产商Add() {
        return PREFIX + "生产商_add.html";
    }

    /**
     * 跳转到修改生产商管理
     */
    @RequestMapping("/生产商_update/{生产商Id}")
    public String 生产商Update(@PathVariable Integer 生产商Id, Model model) {
        生产商 生产商 = 生产商Service.selectById(生产商Id);
        model.addAttribute("item",生产商);
        LogObjectHolder.me().set(生产商);
        return PREFIX + "生产商_edit.html";
    }

    /**
     * 获取生产商管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if (ToolUtil.isEmpty(condition)) {
            return 生产商Service.selectList(null);
        } else {
            //如果不为空，则按照业务名称查询
            EntityWrapper<生产商> entityWrapper = new EntityWrapper<>();
            Wrapper<生产商> wrapper = entityWrapper.like("id", condition);
            return 生产商Service.selectList(wrapper);
        }
    }

    /**
     * 新增生产商管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(生产商 生产商) {
        生产商Service.insert(生产商);
        return SUCCESS_TIP;
    }

    /**
     * 删除生产商管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long 生产商Id) {
        生产商Service.deleteById(生产商Id);
        return SUCCESS_TIP;
    }

    /**
     * 修改生产商管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(生产商 生产商) {
        生产商Service.updateById(生产商);
        return SUCCESS_TIP;
    }

    /**
     * 生产商管理详情
     */
    @RequestMapping(value = "/detail/{生产商Id}")
    @ResponseBody
    public Object detail(@PathVariable("生产商Id") Integer 生产商Id) {
        return 生产商Service.selectById(生产商Id);
    }
}
