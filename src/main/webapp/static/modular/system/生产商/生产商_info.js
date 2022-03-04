/**
 * 初始化生产商管理详情对话框
 */
var 生产商InfoDlg = {
    生产商InfoData : {}
};

/**
 * 清除数据
 */
生产商InfoDlg.clearData = function() {
    this.生产商InfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
生产商InfoDlg.set = function(key, val) {
    this.生产商InfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
生产商InfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
生产商InfoDlg.close = function() {
    parent.layer.close(window.parent.生产商.layerIndex);
}

/**
 * 收集数据
 */
生产商InfoDlg.collectData = function() {
    this
    .set('id')
    .set('生产商名称')
    .set('联系人')
    .set('生产商地址')
    .set('联系电话')
    .set('邮编');
}

/**
 * 提交添加
 */
生产商InfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/生产商/add", function(data){
        Feng.success("添加成功!");
        window.parent.生产商.table.refresh();
        生产商InfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.生产商InfoData);
    ajax.start();
}

/**
 * 提交修改
 */
生产商InfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/生产商/update", function(data){
        Feng.success("修改成功!");
        window.parent.生产商.table.refresh();
        生产商InfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.生产商InfoData);
    ajax.start();
}

$(function() {

});
