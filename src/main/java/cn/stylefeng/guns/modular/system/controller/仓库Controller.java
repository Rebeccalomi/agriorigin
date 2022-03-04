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
import cn.stylefeng.guns.modular.system.model.仓库;
import cn.stylefeng.guns.modular.system.service.I仓库Service;

/**
 * 仓库管理控制器
 *
 * @author fengshuonan
 * @Date 2020-01-16 14:58:59
 */
@Controller
@RequestMapping("/仓库")
public class 仓库Controller extends BaseController {

    private String PREFIX = "/system/仓库/";

    @Autowired
    private I仓库Service 仓库Service;

    /**
     * 跳转到仓库管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "仓库.html";
    }

    /**
     * 跳转到添加仓库管理
     */
    @RequestMapping("/仓库_add")
    public String 仓库Add() {
        return PREFIX + "仓库_add.html";
    }

    /**
     * 跳转到修改仓库管理
     */
    @RequestMapping("/仓库_update/{仓库Id}")
    public String 仓库Update(@PathVariable Integer 仓库Id, Model model) {
        仓库 仓库 = 仓库Service.selectById(仓库Id);
        model.addAttribute("item",仓库);
        LogObjectHolder.me().set(仓库);
        return PREFIX + "仓库_edit.html";
    }

    /**
     * 获取仓库管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if (ToolUtil.isEmpty(condition)) {
            return 仓库Service.selectList(null);
        } else {
            //如果不为空，则按照业务名称查询
            EntityWrapper<仓库> entityWrapper = new EntityWrapper<>();
            Wrapper<仓库> wrapper = entityWrapper.like("id", condition);
            return 仓库Service.selectList(wrapper);
        }
    }

    /**
     * 新增仓库管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(仓库 仓库) {
        仓库Service.insert(仓库);
        return SUCCESS_TIP;
    }

    /**
     * 删除仓库管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long 仓库Id) {
        仓库Service.deleteById(仓库Id);
        return SUCCESS_TIP;
    }

    /**
     * 修改仓库管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(仓库 仓库) {
        仓库Service.updateById(仓库);
        return SUCCESS_TIP;
    }

    /**
     * 仓库管理详情
     */
    @RequestMapping(value = "/detail/{仓库Id}")
    @ResponseBody
    public Object detail(@PathVariable("仓库Id") Integer 仓库Id) {
        return 仓库Service.selectById(仓库Id);
    }
}
