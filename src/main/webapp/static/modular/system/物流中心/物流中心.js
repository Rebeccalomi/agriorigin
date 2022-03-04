/**
 * 物流中心管理管理初始化
 */
var 物流中心 = {
    id: "物流中心Table",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
物流中心.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '物流中心编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '物流中心名称', field: '物流中心名称', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: '地址', visible: true, align: 'center', valign: 'middle'},
            {title: '电话号码', field: '电话号码', visible: true, align: 'center', valign: 'middle'},
            {title: '邮编', field: '邮编', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
物流中心.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        物流中心.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加物流中心管理
 */
物流中心.openAdd物流中心 = function () {
    var index = layer.open({
        type: 2,
        title: '添加物流中心管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/物流中心/物流中心_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看物流中心管理详情
 */
物流中心.open物流中心Detail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '物流中心管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/物流中心/物流中心_update/' + 物流中心.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除物流中心管理
 */
物流中心.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/物流中心/delete", function (data) {
            Feng.success("删除成功!");
            物流中心.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("物流中心Id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询物流中心管理列表
 */
物流中心.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    物流中心.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = 物流中心.initColumn();
    var table = new BSTable(物流中心.id, "/物流中心/list", defaultColunms);
    table.setPaginationType("client");
    物流中心.table = table.init();
});
