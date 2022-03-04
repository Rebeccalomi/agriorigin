/**
 * 初始化区块链管理详情对话框
 */
var EggGethInfoDlg = {
    eggGethInfoData : {}
};

/**
 * 清除数据
 */
EggGethInfoDlg.clearData = function() {
    this.eggGethInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EggGethInfoDlg.set = function(key, val) {
    this.eggGethInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EggGethInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
EggGethInfoDlg.close = function() {
    parent.layer.close(window.parent.EggGeth.layerIndex);
}

/**
 * 收集数据
 */
EggGethInfoDlg.collectData = function() {
    this
    .set('id')
    .set('geth')
    .set('info');
}

/**
 * 提交添加
 */
EggGethInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/eggGeth/add", function(data){
        Feng.success("添加成功!");
        window.parent.EggGeth.table.refresh();
        EggGethInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.eggGethInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
EggGethInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/eggGeth/update", function(data){
        Feng.success("修改成功!");
        window.parent.EggGeth.table.refresh();
        EggGethInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.eggGethInfoData);
    ajax.start();
}

$(function() {

});
