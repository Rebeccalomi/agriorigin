package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.modular.system.model.生产商;
import cn.stylefeng.guns.modular.web3j.generatePassword;
import cn.stylefeng.guns.modular.web3j.newAccounts;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jdk.internal.dynalink.support.NameCodec;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.鸡蛋;
import cn.stylefeng.guns.modular.system.service.I鸡蛋Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 鸡蛋批次管理控制器
 *
 * @author fengshuonan
 * @Date 2020-01-16 15:00:01
 */
@Controller
@RequestMapping("/鸡蛋")
public class 鸡蛋Controller extends BaseController {

    private String PREFIX = "/system/鸡蛋/";
    private String content;
    newAccounts abc=new newAccounts();

    @Autowired
    private I鸡蛋Service 鸡蛋Service;


    /**
     * 跳转到鸡蛋批次管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "鸡蛋.html";
    }

    /**
     * 跳转到添加鸡蛋批次管理
     */
    @RequestMapping("/鸡蛋_add")
    public String 鸡蛋Add() {
        return PREFIX + "鸡蛋_add.html";
    }

    /**
     * 跳转到修改鸡蛋批次管理
     */
    @RequestMapping("/鸡蛋_update/{鸡蛋Id}")
    public String 鸡蛋Update(@PathVariable Integer 鸡蛋Id, Model model) {
        鸡蛋 鸡蛋 = 鸡蛋Service.selectById(鸡蛋Id);
        model.addAttribute("item",鸡蛋);
        LogObjectHolder.me().set(鸡蛋);
        return PREFIX + "鸡蛋_edit.html";
    }

    @RequestMapping("/erweima/{uuid}")
    public String erweima(@PathVariable String uuid,Model model){
            QrCodeUtil qrCodeUtil=new QrCodeUtil();
            String base64Img = qrCodeUtil.creatRrCode("http://www.agriorigin.cn/"+uuid,300,300);
            model.addAttribute("base64Img",base64Img);
            return PREFIX + "鸡蛋_二维码.html";
         }



    /**
     * 获取鸡蛋批次管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if (ToolUtil.isEmpty(condition)) {
            return 鸡蛋Service.selectList(null);
        } else {
            //如果不为空，则按照业务名称查询
            EntityWrapper<鸡蛋> entityWrapper = new EntityWrapper<>();
            Wrapper<鸡蛋> wrapper = entityWrapper.like("id", condition);
            return 鸡蛋Service.selectList(wrapper);
        }
    }

    /**
     * 新增鸡蛋批次管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(鸡蛋 鸡蛋) throws Exception {
        鸡蛋.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
        鸡蛋.setId(3);
        鸡蛋Service.insert(鸡蛋);
        return SUCCESS_TIP;
    }

    /**
     * 删除鸡蛋批次管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long 鸡蛋Id) {
        鸡蛋Service.deleteById(鸡蛋Id);
        return SUCCESS_TIP;
    }

    /**
     * 修改鸡蛋批次管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(鸡蛋 鸡蛋) {
        鸡蛋Service.updateById(鸡蛋);
        return SUCCESS_TIP;
    }

    /**
     * 鸡蛋批次管理详情
     */
    @RequestMapping(value = "/detail/{鸡蛋Id}")
    @ResponseBody
    public Object detail(@PathVariable("鸡蛋Id") Integer 鸡蛋Id) {
        return 鸡蛋Service.selectById(鸡蛋Id);
    }

}
