/**
 * 初始化物流中心管理详情对话框
 */
var 物流中心InfoDlg = {
    物流中心InfoData : {}
};

/**
 * 清除数据
 */
物流中心InfoDlg.clearData = function() {
    this.物流中心InfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
物流中心InfoDlg.set = function(key, val) {
    this.物流中心InfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
物流中心InfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
物流中心InfoDlg.close = function() {
    parent.layer.close(window.parent.物流中心.layerIndex);
}

/**
 * 收集数据
 */
物流中心InfoDlg.collectData = function() {
    this
    .set('id')
    .set('物流中心名称')
    .set('地址')
    .set('电话号码')
    .set('邮编');
}

/**
 * 提交添加
 */
物流中心InfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/物流中心/add", function(data){
        Feng.success("添加成功!");
        window.parent.物流中心.table.refresh();
        物流中心InfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.物流中心InfoData);
    ajax.start();
}

/**
 * 提交修改
 */
物流中心InfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/物流中心/update", function(data){
        Feng.success("修改成功!");
        window.parent.物流中心.table.refresh();
        物流中心InfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.物流中心InfoData);
    ajax.start();
}

$(function() {

});
