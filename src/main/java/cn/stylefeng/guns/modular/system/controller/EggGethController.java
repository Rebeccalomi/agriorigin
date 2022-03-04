package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.EggGeth;
import cn.stylefeng.guns.modular.system.service.IEggGethService;

/**
 * 区块链管理控制器
 *
 * @author fengshuonan
 * @Date 2020-05-03 22:39:30
 */
@Controller
@RequestMapping("/eggGeth")
public class EggGethController extends BaseController {

    private String PREFIX = "/system/eggGeth/";

    @Autowired
    private IEggGethService eggGethService;

    /**
     * 跳转到区块链管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "eggGeth.html";
    }

    /**
     * 跳转到添加区块链管理
     */
    @RequestMapping("/eggGeth_add")
    public String eggGethAdd() {
        return PREFIX + "eggGeth_add.html";
    }

    /**
     * 跳转到修改区块链管理
     */
    @RequestMapping("/eggGeth_update/{eggGethId}")
    public String eggGethUpdate(@PathVariable Integer eggGethId, Model model) {
        EggGeth eggGeth = eggGethService.selectById(eggGethId);
        model.addAttribute("item",eggGeth);
        LogObjectHolder.me().set(eggGeth);
        return PREFIX + "eggGeth_edit.html";
    }

    /**
     * 获取区块链管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return eggGethService.selectList(null);
    }

    /**
     * 新增区块链管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(EggGeth eggGeth) {
        eggGethService.insert(eggGeth);
        return SUCCESS_TIP;
    }

    /**
     * 删除区块链管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer eggGethId) {
        eggGethService.deleteById(eggGethId);
        return SUCCESS_TIP;
    }

    /**
     * 修改区块链管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(EggGeth eggGeth) {
        eggGethService.updateById(eggGeth);
        return SUCCESS_TIP;
    }

    /**
     * 区块链管理详情
     */
    @RequestMapping(value = "/detail/{eggGethId}")
    @ResponseBody
    public Object detail(@PathVariable("eggGethId") Integer eggGethId) {
        return eggGethService.selectById(eggGethId);
    }
}
