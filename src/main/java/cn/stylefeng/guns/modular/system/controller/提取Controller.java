package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.modular.system.model.EggGeth;
import cn.stylefeng.guns.modular.system.service.IEggGethService;
import cn.stylefeng.guns.modular.web3j.generatePassword;
import cn.stylefeng.guns.modular.web3j.newAccounts;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sun.org.apache.xml.internal.serializer.AttributesImplSerializer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.提取;
import cn.stylefeng.guns.modular.system.service.I提取Service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 物流管理控制器
 *
 * @author fengshuonan
 * @Date 2020-01-16 14:59:20
 */
@Controller
@RequestMapping("/提取")
public class 提取Controller extends BaseController {

    private String PREFIX = "/system/提取/";
    private 提取 提取=new 提取();
    private EggGeth Geth=new EggGeth();
    @Autowired
    private I提取Service 提取Service;
    @Autowired
    private IEggGethService EggGethService;
    newAccounts abc=new newAccounts();
    /**
     * 跳转到物流管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "提取.html";
    }

    /**
     * 跳转到添加物流管理
     */
    @RequestMapping("/提取_add")
    public String 提取Add() {
        return PREFIX + "提取_add.html";
    }

    /**
     * 跳转到修改物流管理
     */
    @RequestMapping("/提取_update/{提取Id}")
    public String 提取Update(@PathVariable Integer 提取Id, Model model) {
        提取 提取 = 提取Service.selectById(提取Id);
        model.addAttribute("item",提取);
        LogObjectHolder.me().set(提取);
        return PREFIX + "提取_edit.html";
    }

    /**
     * 获取物流管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if (ToolUtil.isEmpty(condition)) {
            return 提取Service.selectList(null);
        } else {
            提取 a=new 提取();
            a.setId(Integer.valueOf(condition));
            return 提取Service.getList1(a);
        }
    }


    /**
     * 新增物流管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(提取 提取) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> maps = new HashMap<>();
        BigInteger a=new BigInteger("0");
        Date date = df.parse(df.format(new Date()));
        提取.set时间(date);
        提取.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
        maps=abc.newTransaction("0xf6581b9d1d7e8d58b392035cb4cbc467b4953c86",a,"123456","0xf5e079ac84cf6567faffc6b1ba96ac3abdca6835","/root/privatechain/data/keystore/UTC--2020-03-30T04-00-58.928940570Z--f6581b9d1d7e8d58b392035cb4cbc467b4953c86",提取.getUuid()+提取.get物流中心名称()+提取.get时间()+提取.get检测结果());
        Geth.setGeth((String) maps.get("transactionHash"));
        Geth.setInfo(提取.getUuid()+" "+提取.get时间()+" "+提取.get检测结果());
        Geth.setId(1);
        System.out.println(Geth.getInfo());
        EggGethService.insert(Geth);
        提取.setGeth(Geth.getGeth());
        提取Service.insert(提取);
        提取Service.update123(提取);
        return SUCCESS_TIP;
    }

    /**
     * 删除物流管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String 提取Id) {
        提取.setUuid(提取Id);
        提取Service.deleteById(提取);
        return SUCCESS_TIP;
    }

    /**
     * 修改物流管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(提取 提取) {
        提取Service.updateById(提取);
        return SUCCESS_TIP;
    }

    /**
     * 物流管理详情
     */
    @RequestMapping(value = "/detail/{提取Id}")
    @ResponseBody
    public Object detail(@PathVariable("提取Id") String 提取uuid) {
        提取 b=new 提取();
        b.setUuid(提取uuid);
        return 提取Service.selectById(b);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
