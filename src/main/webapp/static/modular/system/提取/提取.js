/**
 * 物流管理管理初始化
 */
var 提取 = {
    id: "提取Table",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
提取.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '鸡蛋批次号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '防伪标识码', field: 'uuid', visible: true, align: 'center', valign: 'middle'},
            {title: '物流中心编号', field: '物流中心编号', visible: true, align: 'center', valign: 'middle'},
            {title: '仓库编号', field: '仓库编号', visible: true, align: 'center', valign: 'middle'},
            {title: '检测结果', field: '检测结果', visible: true, align: 'center', valign: 'middle'},
            {title: '时间', field: '时间', visible: true, align: 'center', valign: 'middle'},
            {title: '是否流入市场', field: '是否流入市场', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
提取.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        提取.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加物流管理
 */
提取.openAdd提取 = function () {
    var index = layer.open({
        type: 2,
        title: '添加物流管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/提取/提取_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看物流管理详情
 */
提取.open提取Detail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '物流管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/提取/提取_update/' + 提取.seItem.uuid
        });
        this.layerIndex = index;
    }
};

/**
 * 删除物流管理
 */
提取.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/提取/delete", function (data) {
            Feng.success("删除成功!");
            提取.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("提取Id",this.seItem.uuid);
        ajax.start();
    }
};

/**
 * 查询物流管理列表
 */
提取.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    提取.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = 提取.initColumn();
    var table = new BSTable(提取.id, "/提取/list", defaultColunms);
    table.setPaginationType("client");
    提取.table = table.init();
});
