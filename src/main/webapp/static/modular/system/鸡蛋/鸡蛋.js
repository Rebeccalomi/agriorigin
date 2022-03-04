/**
 * 鸡蛋批次管理管理初始化
 */
var 鸡蛋 = {
    id: "鸡蛋Table",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
鸡蛋.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '批次号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '防伪码', field: 'uuid', visible: true, align: 'center', valign: 'middle'},
            {title: '货物名称', field: '货物名称', visible: true, align: 'center', valign: 'middle'},
            {title: '货物重量', field: '货物重量', visible: true, align: 'center', valign: 'middle'},
            {title: '货物体积', field: '货物体积', visible: true, align: 'center', valign: 'middle'},
            {title: '生产商编号', field: '生产商编号', visible: true, align: 'center', valign: 'middle'},
            {title: '存放地点', field: '存放地点', visible: true, align: 'center', valign: 'middle'},
            {title: '库存', field: '库存', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
鸡蛋.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        鸡蛋.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加鸡蛋批次管理
 */
鸡蛋.openAdd鸡蛋 = function () {
    var index = layer.open({
        type: 2,
        title: '添加鸡蛋批次管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/鸡蛋/鸡蛋_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看鸡蛋批次管理详情
 */
鸡蛋.open鸡蛋Detail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '鸡蛋批次管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/鸡蛋/鸡蛋_update/' + 鸡蛋.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 生成二维码
 */
鸡蛋.open二维码 = function(){
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '鸡蛋二维码',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/鸡蛋/erweima/' + 鸡蛋.seItem.uuid
        });
        this.layerIndex = index;
    }
};

/**
 * 删除鸡蛋批次管理
 */
鸡蛋.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/鸡蛋/delete", function (data) {
            Feng.success("删除成功!");
            鸡蛋.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("鸡蛋Id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询鸡蛋批次管理列表
 */
鸡蛋.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    鸡蛋.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = 鸡蛋.initColumn();
    var table = new BSTable(鸡蛋.id, "/鸡蛋/list", defaultColunms);
    table.setPaginationType("client");
    鸡蛋.table = table.init();
});
