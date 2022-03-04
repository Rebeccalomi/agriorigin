package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.modular.system.model.生产商;
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
import cn.stylefeng.guns.modular.system.model.物流中心;
import cn.stylefeng.guns.modular.system.service.I物流中心Service;

/**
 * 物流中心管理控制器
 *
 * @author fengshuonan
 * @Date 2020-01-16 14:59:43
 */
@Controller
@RequestMapping("/物流中心")
public class 物流中心Controller extends BaseController {

    private String PREFIX = "/system/物流中心/";

    @Autowired
    private I物流中心Service 物流中心Service;

    /**
     * 跳转到物流中心管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "物流中心.html";
    }

    /**
     * 跳转到添加物流中心管理
     */
    @RequestMapping("/物流中心_add")
    public String 物流中心Add() {
        return PREFIX + "物流中心_add.html";
    }

    /**
     * 跳转到修改物流中心管理
     */
    @RequestMapping("/物流中心_update/{物流中心Id}")
    public String 物流中心Update(@PathVariable Integer 物流中心Id, Model model) {
        物流中心 物流中心 = 物流中心Service.selectById(物流中心Id);
        model.addAttribute("item",物流中心);
        LogObjectHolder.me().set(物流中心);
        return PREFIX + "物流中心_edit.html";
    }

    /**
     * 获取物流中心管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if (ToolUtil.isEmpty(condition)) {
            return 物流中心Service.selectList(null);
        } else {
            //如果不为空，则按照业务名称查询
            EntityWrapper<物流中心> entityWrapper = new EntityWrapper<>();
            Wrapper<物流中心> wrapper = entityWrapper.like("id", condition);
            return 物流中心Service.selectList(wrapper);
        }
    }

    /**
     * 新增物流中心管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(物流中心 物流中心) {
        物流中心Service.insert(物流中心);
        return SUCCESS_TIP;
    }

    /**
     * 删除物流中心管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long 物流中心Id) {
        物流中心Service.deleteById(物流中心Id);
        return SUCCESS_TIP;
    }

    /**
     * 修改物流中心管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(物流中心 物流中心) {
        物流中心Service.updateById(物流中心);
        return SUCCESS_TIP;
    }

    /**
     * 物流中心管理详情
     */
    @RequestMapping(value = "/detail/{物流中心Id}")
    @ResponseBody
    public Object detail(@PathVariable("物流中心Id") Integer 物流中心Id) {
        return 物流中心Service.selectById(物流中心Id);
    }
}
