/**
 * 初始化鸡蛋批次管理详情对话框
 */
var 鸡蛋InfoDlg = {
    鸡蛋InfoData : {}
};

/**
 * 清除数据
 */
鸡蛋InfoDlg.clearData = function() {
    this.鸡蛋InfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
鸡蛋InfoDlg.set = function(key, val) {
    this.鸡蛋InfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
鸡蛋InfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
鸡蛋InfoDlg.close = function() {
    parent.layer.close(window.parent.鸡蛋.layerIndex);
}

/**
 * 收集数据
 */
鸡蛋InfoDlg.collectData = function() {
    this
    .set('id')
    .set('货物名称')
    .set('货物重量')
    .set('货物体积')
    .set('生产商编号')
    .set('存放地点')
    .set('库存');
}

/**
 * 提交添加
 */
鸡蛋InfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/鸡蛋/add", function(data){
        Feng.success("添加成功!");
        window.parent.鸡蛋.table.refresh();
        鸡蛋InfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.鸡蛋InfoData);
    ajax.start();
}

/**
 * 提交修改
 */
鸡蛋InfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/鸡蛋/update", function(data){
        Feng.success("修改成功!");
        window.parent.鸡蛋.table.refresh();
        鸡蛋InfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.鸡蛋InfoData);
    ajax.start();
}

$(function() {

});
