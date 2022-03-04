/**
 * 初始化物流管理详情对话框
 */
var 提取InfoDlg = {
    提取InfoData : {}
};

/**
 * 清除数据
 */
提取InfoDlg.clearData = function() {
    this.提取InfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
提取InfoDlg.set = function(key, val) {
    this.提取InfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
提取InfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
提取InfoDlg.close = function() {
    parent.layer.close(window.parent.提取.layerIndex);
}

/**
 * 收集数据
 */
提取InfoDlg.collectData = function() {
    this
    .set('id')
    .set('物流中心编号')
    .set('仓库编号')
    .set('检测结果')
    .set('时间')
    .set('是否流入市场');
}

/**
 * 提交添加
 */
提取InfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/提取/add", function(data){
        Feng.success("添加成功!");
        window.parent.提取.table.refresh();
        提取InfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.提取InfoData);
    ajax.start();
}

/**
 * 提交修改
 */
提取InfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/提取/update", function(data){
        Feng.success("修改成功!");
        window.parent.提取.table.refresh();
        提取InfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.提取InfoData);
    ajax.start();
}

$(function() {

});
