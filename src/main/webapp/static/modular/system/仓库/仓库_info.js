/**
 * 初始化仓库管理详情对话框
 */
var 仓库InfoDlg = {
    仓库InfoData : {}
};

/**
 * 清除数据
 */
仓库InfoDlg.clearData = function() {
    this.仓库InfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
仓库InfoDlg.set = function(key, val) {
    this.仓库InfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
仓库InfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
仓库InfoDlg.close = function() {
    parent.layer.close(window.parent.仓库.layerIndex);
}

/**
 * 收集数据
 */
仓库InfoDlg.collectData = function() {
    this
    .set('id')
    .set('所属物流中心编号')
    .set('仓库地址')
    .set('仓库性质')
    .set('仓库总量')
    .set('电话');
}

/**
 * 提交添加
 */
仓库InfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/仓库/add", function(data){
        Feng.success("添加成功!");
        window.parent.仓库.table.refresh();
        仓库InfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.仓库InfoData);
    ajax.start();
}

/**
 * 提交修改
 */
仓库InfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/仓库/update", function(data){
        Feng.success("修改成功!");
        window.parent.仓库.table.refresh();
        仓库InfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.仓库InfoData);
    ajax.start();
}

$(function() {

});
